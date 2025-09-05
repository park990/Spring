package com.sist.ex0905_deptsearch.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages="com.sist.ex0905_deptsearch.mapper")
public class DeptConf{

    @Bean
    public SqlSessionFactory sf(DataSource ds) throws  Exception{
        SqlSessionFactoryBean sBean = new SqlSessionFactoryBean();
        sBean.setDataSource(ds);

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sBean.setMapperLocations(resolver.getResources("classpath:mybatis/mapper/**/*.xml"));

        return sBean.getObject();
    }

    @Bean
    public SqlSessionTemplate st(SqlSessionFactory factory){
        return new SqlSessionTemplate(factory);
    }

}
