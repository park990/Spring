package com.sist.ex0918.domain.member.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sist.ex0918.domain.member.repository.MemberRepository;
import com.sist.ex0918.domain.member.entity.Member;
import com.sist.ex0918.global.jwt.JwtProvider;
import com.sist.ex0918.global.result.ResultData;
import com.sist.ex0918.global.security.JwtUser;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository mRepository;
    private final JwtProvider JwtProvider;
    private final PasswordEncoder passwordEncoder;

    public Member join(String mid, String mname, String mpwd) {
        Member mem = Member.builder()
                .mid(mid)
                .mname(mname)
                .mpwd(mpwd)
                .build();
        return mRepository.save(mem);
    }

    public Member authAndMakeToken(String mid, String mpwd) {
        Member member = null;
        String accessToken = null;
        try {
            member = mRepository.findByMid(mid).orElseThrow(() -> new RuntimeException("존재하지 않음"));

            // 위는 mid 값만 가지고 검색한 Member이므로 다시 비밀번호가 맞는지 확인해야한다.
            if (passwordEncoder.matches(mpwd, member.getMpwd())) {
                // 회원정보를 가지고 token 생성
                Map<String, Object> map = new HashMap<>();
                map.put("idx", member.getB_idx());
                map.put("mid", member.getMid());
                // map.put("mpwd", member.getMpwd()); // 토큰에 넣는 것은 좋지 않음.
                map.put("mname", member.getMname());
                map.put("write_date", member.getWrite_date());

                accessToken = JwtProvider.getAccessToken(map);
                String refreshToken = JwtProvider.getRefreshToken(map);

                member.setAccessToken(accessToken);
                member.setRefreshToken(refreshToken);

                // DB에 UPDATE 할거면 여기서 해도 된다.
             mRepository.updateRefreshToken(member.getB_idx(),member.getRefreshToken());
            } // if 의 끝
            else{
                member = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ACESSTOKEN::::" + accessToken);
        }
        return member;
    }

    public ResultData<String> refreshAccessToken(String refreshToString){
        Member member= null;
        member = mRepository.findByRefreshToken(refreshToString).orElseThrow(()->
            new RuntimeException("존재하지않는 token"));
            Map<String, Object> map = new HashMap<>();

            map.put("idx", member.getB_idx());
            map.put("mid", member.getMid());
            map.put("mname", member.getMname());
            map.put("write_date", member.getWrite_date());
        
        String accessToken= JwtProvider.getAccessToken(map);

        

        int cnt= 0;
        String msg="fail";
        if(member!=null){
            cnt=1;
            msg="success";
        }
        return ResultData.jsonReturn(cnt, msg,accessToken);
    }


    // payload 뽑아내기
    public JwtUser getUserFromAccessToken(String accessToken){
        Map<String, Object> payload =JwtProvider.getClaims(accessToken);
         
        String mid = (String) payload.get("mid");
        String mname = (String) payload.get("mname");
        List<GrantedAuthority> authorities = new ArrayList<>();

        return new JwtUser(mid, mname, "", authorities);
    }

    public boolean validateToken(String token){
        return JwtProvider.verify(token);
    }
}
