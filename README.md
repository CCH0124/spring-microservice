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