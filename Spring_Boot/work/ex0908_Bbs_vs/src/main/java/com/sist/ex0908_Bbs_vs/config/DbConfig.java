package com.sist.ex0908_Bbs_vs.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(basePackages = "com.sist.ex0908_Bbs_vs.mapper")
public class DbConfig {

    @Bean
    public SqlSessionFactory sf(DataSource ds) throws Exception{
        SqlSessionFactoryBean sbean = new SqlSessionFactoryBean(); 
        sbean.setDataSource(ds);

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sbean.addMapperLocations(resolver.getResources("classpath:mapper/**/*.xml"));
        return sbean.getObject();
    }

    @Bean
    SqlSessionTemplate sessionTemplate(SqlSessionFactory sf){
        return new SqlSessionTemplate(sf);
    }
    
}
