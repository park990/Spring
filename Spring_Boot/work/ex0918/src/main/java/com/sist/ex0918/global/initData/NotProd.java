package com.sist.ex0918.global.initData;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sist.ex0918.domain.bbs.service.BbsService;
import com.sist.ex0918.domain.member.entity.Member;
import com.sist.ex0918.domain.member.service.MemberService;

@Configuration
@Profile({"dev","test"})
public class NotProd {
    

    // 가짜 데이터(개발때만 미리 데이터를 넣어주기 위함)
    @Bean
    CommandLineRunner initData(BbsService bbsService,MemberService memberService,
                                PasswordEncoder passwordEncoder){
        
        String pwd = passwordEncoder.encode("1111");                            
        
        return args->{
        Member mem3 = memberService.join("maru", "마루치", pwd);
        Member mem4 = memberService.join("admin", "어두민", pwd);

        bbsService.create("제목1","마루치","테스트입니다");
        bbsService.create("제목2","김진환","띵호와");
        bbsService.create("제목3","허가람","안녕하세요");
        bbsService.create("제목4","전한결","하이오이");
        };
    }
}
