package com.review.ex0919_jaeyoon_jwt_important.global.dummy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.review.ex0919_jaeyoon_jwt_important.domain.member.entity.MemEntity;
import com.review.ex0919_jaeyoon_jwt_important.domain.member.service.MemService;

@Configuration
@Profile({"dev", "test"})
public class DummyData {

    @Bean
    CommandLineRunner DummyData(MemService mService, PasswordEncoder passwordEncoder){
        String pPw = passwordEncoder.encode("1111");
        
        return new CommandLineRunner() {

            @Override
            public void run(String... args) throws Exception {
                MemEntity mem = mService.insert("aaa", pPw, "Park");
                MemEntity mem2 = mService.insert("bbb", pPw, "Kim");
                MemEntity mem3 = mService.insert("ccc", pPw, "Lee");
            };
            
        };
    }
    
    
}
