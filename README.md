postgresql install

```yml
version: '3.7'
services:
  postgres:
    container_name: postgres
    image: postgres
    restart: always
    ports:
      - "5432:5432"
    hostname: postgres
    environment:
      - POSTGRES_PASSWORD=123456
    volumes:
      - ./init.sql:/docker-entrypoint-initdb.d/init.sql
      - postgresql-data:/var/lib/postgresql/data
    healthcheck:
        test: ["CMD-SHELL", "pg_isready -U postgres"]
        interval: 30s
        timeout: 10s
        retries: 5


volumes:
  postgresql-data:
```

```bash=
docker-compose up -d
docker-compose down --volumes # remove
```

```bash=
employee-# \dt
       List of relations
 Schema | Name | Type  | Owner
--------+------+-------+--------
 public | user | table | itachi
(1 row)

```

## Postman Test

### Add User
```
post http://localhost:8080/add
```

Request
```json
{
    "name": "itahi",
    "age": 26,
    "email": "123@gmail.com",
    "tel": "0900000000"
}
```

Response

```json
{
    "code": 200,
    "msg": "success",
    "data": {
        "id": "1",
        "name": "itahi",
        "age": 26,
        "email": "123@gmail.com",
        "tel": "0900000000"
    }
}
```

### Get user

```
GET http://localhost:8080/1
<!-- 1 是 id -->
```

Response

```json
{
    "code": 200,
    "msg": "success",
    "data": {
        "id": "1",
        "name": "itahi",
        "age": 26,
        "email": "123@gmail.com",
        "tel": "0900000000"
    }
}
```

### Update User

```
PUT http://localhost:8080/update
```

Request

```json
{
    "id": 1,
    "name": "itahi",
    "age": 28,
    "email": "1234@gmail.com",
    "tel": "0900000000"
}
```

Response

```json
{
    "code": 200,
    "msg": "success",
    "data": {
        "id": "1",
        "name": "itahi",
        "age": 28,
        "email": "1234@gmail.com",
        "tel": "0900000000"
    }
}
```

### Delete User

```
DELETE http://localhost:8080/1
```

Response
```json
{
    "code": 200,
    "msg": "success",
    "data": {
        "status": true
    }
}
```

## Spring Native
使用 [GraalVM](https://www.graalvm.org/) [native-image](https://www.graalvm.org/reference-manual/native-image/) 編譯器將 Spring 應用程式編譯為本機可執行檔案。native-image 相比 JVM 更好在容器或 K8s 技術尚使用，因為其工作負載或微服務都更加優化，使用 native-image 在即時啟動、即時峰值性能和減少記憶體消耗都比 JVM 更加有優勢。但是，構建native-image 比基於 JVM 的映像需要更多時間。在背後 `Spring AOT` 帶來了構建時的一些配置處理並簡化了 native-image 編譯過程。

更詳細可參閱[官方文檔](https://docs.spring.io/spring-native/docs/current/reference/htmlsingle/#overview)。


添加一個指向 Spring 儲存庫的鏈接，以下載之後部分所需的依賴項和插件

```xml
<project>
    ...
	<repositories>
		<repository>
			<id>spring-release</id>
			<name>Spring release</name>
			<url>https://repo.spring.io/release</url>
		</repository>
	</repositories>
	<pluginRepositories>
		<pluginRepository>
			<id>spring-release</id>
			<name>Spring release</name>
			<url>https://repo.spring.io/release</url>
		</pluginRepository>
	</pluginRepositories>
</project>    
```

Maven 尚新增 spring native 依賴

```xml
<dependency>
    <groupId>org.springframework.experimental</groupId>
    <artifactId>spring-native</artifactId>
    <version>0.10.0</version>
</dependency>
```

>每個 Spring Native 版本只會支援特定的 Spring Boot 版本

此範例使用 JDK 11 進行編譯

```xml
	<properties>
		<java.version>11</java.version>
		<maven.compiler.target>1.11</maven.compiler.target>
		<maven.compiler.source>1.11</maven.compiler.source>
	</properties>
```

接著使用  `buildpacks` 建構 native-image。使用擁有 native-image 的 `Paketo Java buildpacks` 配置 `spring-boot-maven-plugin`，此方式是強制 GraalVM 版本

```xml
    <build>
        <plugins>
            <plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<image>
						<buildpacks>
							<buildpack>gcr.io/paketo-buildpacks/java-native-image:5.3.0</buildpack>
						</buildpacks>
					</image>
				</configuration>
			</plugin>
		</plugins>
	</build>
```

正規啟用 native image
```xml
<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
    <configuration>
        <image>
            <builder>paketobuildpacks/builder:tiny</builder>
            <env>
                <BP_NATIVE_IMAGE>true</BP_NATIVE_IMAGE>
            </env>
        </image>
    </configuration>
</plugin>
```

需添加 Spring AOT 套件，該套件執行可改善 native-image 的佔用空間和兼容性的提前轉換。

```xml
...
            <plugin>
				<groupId>org.springframework.experimental</groupId>
				<artifactId>spring-aot-maven-plugin</artifactId>
				<version>0.10.2</version>
				<executions>
					<execution>
						<id>test-generate</id>
						<goals>
							<goal>test-generate</goal>
						</goals>
					</execution>
					<execution>
						<id>generate</id>
						<goals>
							<goal>generate</goal>
						</goals>
					</execution>
				</executions>
			</plugin>
...
```


build 和 run 映像檔

```bash
mvn spring-boot:build-image
```

Error，在 github 上的[討論](https://github.com/spring-projects-experimental/spring-native/issues/248)，[官方](https://spring.io/blog/2020/11/23/spring-native-for-graalvm-0-8-3-available-now)上的解釋。
```
org.springframework.experimental:spring-aot-maven-plugin:0.10.2:test-generate (test-generate) on project demo: Build failed during Spring AOT test code generation: ERROR: in 'cch.com.example.demo.config.DataBaseConfig' these methods are directly invoking methods marked @Bean: [masterTransactionManager] - due to the enforced proxyBeanMethods=false for components in a native-image, please consider refactoring to use instance injection. If you are confident this is not going to affect your application, you may turn this check off using -Dspring.native.verify=false. -> [Help 1]
```
待解決...


>spring-boot-devtools AOT 目前不知援因此須將它移除