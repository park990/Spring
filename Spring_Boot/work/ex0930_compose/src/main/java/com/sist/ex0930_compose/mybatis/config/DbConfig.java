package com.sist.ex0930_compose.mybatis.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.sist.ex0930_compose.mybatis.mapper")
public class DbConfig {
    // 현재클래스는 스프링환경이 알아서 호출하며 아래의 @Bean이라는 어노테이션때문에 강제로
    // 함수들을 한번씩 호출하여 스프링환경(Context)에 반환되어 객체들을 등록한다.

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource ds) throws Exception{
        // 반환객체인 sqlsessionfactory를 만드는 객체를 생성하자
        SqlSessionFactoryBean factoryBean=new SqlSessionFactoryBean();

        //factorybean은 비어있는 상태다 인자로 받은 ds를 factorybean에 넣어주자
        factoryBean.setDataSource(ds);

        PathMatchingResourcePatternResolver resolver=new PathMatchingResourcePatternResolver();

        //SQL문장들을 가진 mapper들 지정
        factoryBean.setMapperLocations(resolver.getResources(
                "classpath:mybatis/mapper/**/*.xml"));

        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory factory){
        return new SqlSessionTemplate(factory);
    }
}
