//package com.skymall.config;
//
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.EnableMBeanExport;
//
//import javax.annotation.Resource;
//import javax.sql.DataSource;
//
//@Configuration
//public class SqlSessionConfig {
//    @Resource
//    DataSource dataSource;
//
//    @Bean
//    public SqlSessionFactoryBean creataSqlSessionFactory(){
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSource);
//        return sqlSessionFactoryBean;
//    }
//}
