package com.wally.hiread.setting.db;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;


@Configuration
@MapperScan(basePackages = "com.**.dao")
public class DatabaseConfig {
  @Autowired
  Properties serverConfig;

  @Bean
  public DataSource dataSource() {
    DruidDataSource datasource = new DruidDataSource();
    String driver=serverConfig.getProperty("jdbc.driverClassName");
    String url=serverConfig.getProperty("jdbc.url");
    String username=serverConfig.getProperty("jdbc.username");
    String password=serverConfig.getProperty("jdbc.password");
    datasource.setDriverClassName(driver);
    datasource.setUrl(url);
    datasource.setUsername(username);
    datasource.setPassword(password);
    datasource.setMaxActive(20);
    datasource.setInitialSize(5);
    return datasource;
  }


  @Bean
  public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
    final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
    sessionFactory.setDataSource(dataSource);
    ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    String url = "classpath*:com/**/sqlmap/*.xml";
    Resource[] resources = resolver.getResources(url);
    sessionFactory.setMapperLocations(resources);
    return sessionFactory.getObject();
  }

  @Bean
  public DataSourceTransactionManager transactionManager(DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }

}
