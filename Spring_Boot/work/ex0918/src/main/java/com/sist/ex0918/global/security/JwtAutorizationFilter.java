package com.sist.ex0918.global.security;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sist.ex0918.domain.member.service.MemberService;
import com.sist.ex0918.global.result.ResultData;
import com.sist.ex0918.global.service.RequestService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Component
@RequiredArgsConstructor
public class JwtAutorizationFilter extends OncePerRequestFilter {
    private final RequestService requestService;
    private final MemberService mService;

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

        // accessToken 검증 또는 refreshToken 발급
        String accessToken = requestService.getCookie("accessToken");

        if (!accessToken.isBlank()) {

            // accessToken이 만료되었는지 검증 한 후 받자.
            if (!mService.validateToken(accessToken)) {

                String refreshToken = requestService.getCookie("refreshToken");
                ResultData<String> resultData = mService.refreshAccessToken(refreshToken);

                requestService.setHeaderCookie("accessToken", resultData.getData());

                accessToken = resultData.getData();
            }

            JwtUser jwtUser = mService.getUserFromAccessToken(accessToken);

            // 인가처리
            requestService.setMember(jwtUser);
        }
        filterChain.doFilter(request, response);
    }
}
