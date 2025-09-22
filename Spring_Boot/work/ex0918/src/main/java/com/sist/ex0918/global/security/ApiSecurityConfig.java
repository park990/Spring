package com.sist.ex0918.global.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ApiSecurityConfig {
    private final JwtAutorizationFilter jwtAutorizationFilter;

    @Bean
    public SecurityFilterChain apFilterChain(HttpSecurity http) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable)
            .headers(headers->headers.frameOptions(
                HeadersConfigurer.FrameOptionsConfig::sameOrigin))
            .securityMatcher("/api/**") // 설정된 경로로 들어오는 모든 것들 검사
            .authorizeHttpRequests(
                authorize->authorize.requestMatchers("/api/bbs/**", "/api/members/**").permitAll()
                .requestMatchers(HttpMethod.POST,"/api/members/login").permitAll()
                .requestMatchers(HttpMethod.POST,"/api/members/logout").permitAll()
                .anyRequest().authenticated()
            ).csrf(csrf->csrf.disable() // 토큰검사 비활성화
            ).httpBasic(httpBasic->httpBasic.disable() // httpBasic 로그인 법 비활성화
            // 보안상 취약하며 테스트용 또는 프로토타입 같은 것에서 사용, 
            // 하지만 실제 서비스에서는 JWT, OAuth2 세션 로그인을 선호함
            ).formLogin(formLogin->formLogin.disable() // 폼 로그인 법 비활성화
            ).sessionManagement(sessionManagement->sessionManagement.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS)// 세션 비활성화
            ).addFilterBefore(jwtAutorizationFilter,
                UsernamePasswordAuthenticationFilter.class);
            
                return http.build();
    }
}
