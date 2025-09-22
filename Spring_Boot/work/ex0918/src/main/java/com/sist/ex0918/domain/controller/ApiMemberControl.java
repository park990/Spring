package com.sist.ex0918.domain.controller;

import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.ex0918.domain.member.entity.Member;
import com.sist.ex0918.domain.member.input.MemVO;
import com.sist.ex0918.domain.member.service.MemberService;
import com.sist.ex0918.global.result.ResultData;
import com.sist.ex0918.global.service.RequestService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class ApiMemberControl {
    private final MemberService mService;
    private final HttpServletResponse response;
    private final RequestService requestService;

    @PostMapping("/login")
        public ResultData<Member> login(@RequestBody MemVO member){
     // public ResultData<Member> login(String mid, String mpwd){
            int cnt =0;
            String msg ="fail";
            System.out.println("+++++++++++++++++++++"+member.getMid());

            System.out.println("-----------------------"+member.getMpwd());

            // JWT 생성
            Member mem = mService.authAndMakeToken(member.getMid(), member.getMpwd());
            if (mem!=null){
                ResponseCookie cookie = ResponseCookie.from(
                    "accessToken",mem.getAccessToken()).path("/") // 특정 도메인에서 사용하도록 함
                    .sameSite("None") // CSRF 관련 문제 해결
                    .httpOnly(true) // 클라이언트 등을 통해 쿠키가 탈취된는 것을 방지
                    .secure(true) // 쿠키가 탈취당해도 암호화 되어있어서 안전
                    .build();
                response.addHeader("Set-Cookie",cookie.toString());
                
                cookie = ResponseCookie.from(
                    "refreshToken",mem.getRefreshToken()).path("/") // 특정 도메인에서 사용하도록 함
                    .sameSite("None") // CSRF 관련 문제 해결
                    .httpOnly(true) // 클라이언트 등을 통해 쿠키가 탈취된는 것을 방지
                    .secure(true) // 쿠키가 탈취당해도 암호화 되어있어서 안전
                    .build();
                response.addHeader("Set-Cookie",cookie.toString());
                cnt=1;
                msg="success";
            }
        return ResultData.jsonReturn(cnt, msg, mem);
    }

        @PostMapping("/logout") 
    public ResultData<Member> logout() { 
        requestService.removeHeaderCookie("accessToken"); 
        requestService.removeHeaderCookie("refreshToken"); 
         
        return ResultData.jsonReturn(0, "logout", null); //json전달 
    } 
}
