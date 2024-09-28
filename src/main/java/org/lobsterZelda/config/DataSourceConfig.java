package org.lobsterZelda.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig
{

    @Value("${spring.datasource.username}")
    private String dbUserName;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.driver-class-name}")
    private String dbDriverClassName;

    @Bean
    public DataSource dataSource()
    {
        return DataSourceBuilder
                .create()
                .username(dbUserName)
                .password(dbPassword)
                .url(dbUrl)
                .driverClassName(dbDriverClassName)
                .build();
    }
}
