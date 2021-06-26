package cch.com.example.demo.config;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import java.util.Properties;

@Configuration
@MapperScan(basePackages = { DataBaseConfig.PACKAGE }, sqlSessionFactoryRef = "sqlSessionFactory")
public class DataBaseConfig {

    static final String PACKAGE = "cch.com.example.demo.mapper";

    @Value("${db_classname}")
    private String classname;

    @Value("${db_url}")
    private String url;

    @Value("${db_username}")
    private String username;

    @Value("${db_password}")
    private String password;

    @Value("${db_maximum_pool_size}")
    private String maximumPoolSize;

    @Bean(name = "dataSource")
    @Primary
    public DataSource dataSource() {
        Properties props = new Properties();
        props.setProperty("stringtype", "unspecified");
        final HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(this.classname);
        hikariConfig.setJdbcUrl(this.url);
        hikariConfig.setDataSourceProperties(props);
        hikariConfig.setUsername(this.username);
        hikariConfig.setPassword(this.password);
        hikariConfig.setMaximumPoolSize(Integer.valueOf(this.maximumPoolSize));
        hikariConfig.setConnectionTestQuery("SELECT 1");
        hikariConfig.setPoolName("springHikariCP");
        return new HikariDataSource(hikariConfig);
    }

    @Bean(name = "transactionManager")
    @Primary
    public DataSourceTransactionManager masterTransactionManager() {
        return new DataSourceTransactionManager((dataSource()));
    }

    @Bean(name = "sqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);

        return sessionFactoryBean.getObject();
    }
}