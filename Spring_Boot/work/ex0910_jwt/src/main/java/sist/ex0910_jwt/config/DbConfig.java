package sist.ex0910_jwt.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(basePackages = "sist.ex0910_jwt.mapper")
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
    public SqlSessionTemplate st(SqlSessionFactory sf){
        return new SqlSessionTemplate(sf);
    }

}
