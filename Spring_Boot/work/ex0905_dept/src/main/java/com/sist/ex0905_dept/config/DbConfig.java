package com.sist.ex0905_dept.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.sist.ex0905_dept.mapper")
public class DbConfig {
    // 자동으로 스프링환경이 한번 호출한다.

    @Bean// @Bean이라고 명시 했기 때문에 빈 객체를 만들기 위해 한번 호출하는 것.
    public SqlSessionFactory sqlSessionFactory(DataSource ds) throws Exception {
        // SQLsessionfactory를 생성하는 객체를 만들자
        SqlSessionFactoryBean sbean = new SqlSessionFactoryBean();// ds를 참조 하고 있어야 한다.
        sbean.setDataSource(ds);

        // Sql문장들(mapper)를 인식하기 위해 mapper들이 있는 위치를 지정하자.
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sbean.setMapperLocations(resolver.getResources("classpath:mybatis/mapper/**/*.xml"));

        return sbean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory factory) {
        return new SqlSessionTemplate(factory);
    }
}
