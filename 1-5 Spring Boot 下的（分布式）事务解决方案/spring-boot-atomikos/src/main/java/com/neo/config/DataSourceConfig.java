package com.neo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean(name = "primaryDataSource")
    @Primary
    @ConfigurationProperties("spring.jta.atomikos.datasource.primary")
    public DataSource firstDataSource() {
        return new AtomikosDataSourceBean();
    }

    @Bean(name = "secondaryDataSource")
    @ConfigurationProperties("spring.jta.atomikos.datasource.secondary")
    public DataSource secondDataSource() {
        return new AtomikosDataSourceBean();
    }

}
