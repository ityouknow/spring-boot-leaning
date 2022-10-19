package com.neo.config.druid;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.sql.SQLException;
import javax.sql.DataSource;

@Configuration
public class DruidDBConfig {
    private Logger logger = LoggerFactory.getLogger(DruidDBConfig.class);

    @Autowired
    private DruidConfig druidOneConfig;
    @Autowired
    private DruidConfig druidTwoConfig;
    @Autowired
    private DruidConfig druidConfig;


    @Bean(name = "primaryDataSource")
    public DataSource dataSource() {
        return initDruidDataSource(druidOneConfig);
    }

    @Bean(name = "secondaryDataSource")
    @Primary
    public DataSource secondaryDataSource() {
        return initDruidDataSource(druidTwoConfig);
    }

    private DruidDataSource initDruidDataSource(DruidConfig config) {
        DruidDataSource datasource = new DruidDataSource();

        datasource.setUrl(config.getUrl());
        datasource.setUsername(config.getUsername());
        datasource.setPassword(config.getPassword());
        datasource.setDriverClassName(config.getDriverClassName());
        datasource.setInitialSize(config.getInitialSize());
        datasource.setMinIdle(config.getMinIdle());
        datasource.setMaxActive(config.getMaxActive());

        // common config
        datasource.setMaxWait(druidConfig.getMaxWait());
        datasource.setTimeBetweenEvictionRunsMillis(druidConfig.getTimeBetweenEvictionRunsMillis());
        datasource.setMinEvictableIdleTimeMillis(druidConfig.getMinEvictableIdleTimeMillis());
        datasource.setMaxEvictableIdleTimeMillis(druidConfig.getMaxEvictableIdleTimeMillis());
        datasource.setValidationQuery(druidConfig.getValidationQuery());
        datasource.setTestWhileIdle(druidConfig.isTestWhileIdle());
        datasource.setTestOnBorrow(druidConfig.isTestOnBorrow());
        datasource.setTestOnReturn(druidConfig.isTestOnReturn());
        datasource.setPoolPreparedStatements(druidConfig.isPoolPreparedStatements());
        datasource.setMaxPoolPreparedStatementPerConnectionSize(druidConfig.getMaxPoolPreparedStatementPerConnectionSize());
        try {
            datasource.setFilters(druidConfig.getFilters());
        } catch (SQLException e) {
            logger.error("druid configuration initialization filter : {0}", e);
        }
        datasource.setConnectionProperties(druidConfig.getConnectionProperties());

        return datasource;
    }
}