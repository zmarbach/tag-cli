package com.improving.tagcli.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;


@Configuration
public class TagJdbcConfiguration {
    @Bean //DataSource is a parameter for the JdbcTemplate constructor(which we pass into DatabaseClient constructor).
    // We ask Spring to make a DatabaseClient object (put @Component above it),
    // which requires a JdbcTemplate, which requires a DataSource object
    // ...so we have to @Bean above this so Spring create one using the values we specify below

    public DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/tag?serverTimezone = UTC");
        dataSource.setUsername("zachlocal");
        dataSource.setPassword("Buggywhip22");
        return dataSource;
    }
}
