package com.sist.ex0918.global.initData;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.sist.ex0918.domain.bbs.entity.service.BbsService;

@Configuration

@Profile({"dev","test"})
public class NotProd {
    // 가짜 데이터(개발때만 미리 데이터를 넣어주기 위함)

    @Bean
    CommandLineRunner initData(BbsService bbsService){
        
        return args->{
        bbsService.create("제목1","마루치","테스트입니다");
        bbsService.create("제목2","김진환","띵호와");
        bbsService.create("제목3","허가람","안녕하세요");
        bbsService.create("제목4","전한결","하이오이");
        };
    }
}
