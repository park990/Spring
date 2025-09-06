package com.example.ex0906_bbs_review.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages ="com.example.ex0906_bbs_review.mapper")
public class BbsConf {

    @Bean
    public SqlSessionFactory sf(DataSource ds) throws Exception{
        SqlSessionFactoryBean sb = new SqlSessionFactoryBean();
        sb.setDataSource(ds);

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sb.setMapperLocations(resolver.getResource("classpath:mybatis/mapper/**/*.xml"));
        return sb.getObject();
    }

    @Bean
    public SqlSessionTemplate st(SqlSessionFactory sf){
        return new SqlSessionTemplate(sf);
    }
}
