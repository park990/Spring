package com.sist.ex0918.global.security;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

@Component
public class JwtAutorizationFilter extends OncePerRequestFilter {

    @Override
    @SneakyThrows // try~catch로 예외처리를 해야할 것을.. 명시적 예외처리를
                  // 생략할 수 있도록 해준다.
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        filterChain.doFilter(request, response);

        if (request.getRequestURI().equals("/api/members/login") ||
                request.getRequestURI().equals("/api/members/logout")) {
             filterChain.doFilter(request, response);
            return;
        } // 로그인 또는 로그아웃은 통과해라.

        String accessToken = null;
        if (!accessToken.isBlank()) {
            // 나중에
        }
        filterChain.doFilter(request, response);
    }
}
