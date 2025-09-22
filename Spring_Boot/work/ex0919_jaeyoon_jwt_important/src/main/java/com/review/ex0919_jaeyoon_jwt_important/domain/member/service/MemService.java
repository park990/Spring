package com.review.ex0919_jaeyoon_jwt_important.domain.member.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.review.ex0919_jaeyoon_jwt_important.domain.member.dto.AccessTokenDTO;
import com.review.ex0919_jaeyoon_jwt_important.domain.member.entity.MemEntity;
import com.review.ex0919_jaeyoon_jwt_important.domain.member.repository.MemRepository;
import com.review.ex0919_jaeyoon_jwt_important.global.jwt.JwtGenerate;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemService {

    private final PasswordEncoder passwordEncoder;
    private final MemRepository mRepository;
    private final JwtGenerate jwtGenerate;

    public MemEntity insert(String pid, String pPw, String name) {
        MemEntity mem = MemEntity.builder()
                .pid(pid)
                .pPw(pPw)
                .name(name)
                .build();
        return mRepository.save(mem);
    }

    public Optional<MemEntity> getUser(String pid) {
        return mRepository.findByPid(pid);
    }

    public AccessTokenDTO authAndGenToken(MemEntity insertVO){
       AccessTokenDTO dto=null;

        MemEntity mem = mRepository.findByPid(insertVO.getPid())
                .orElseThrow(()->new RuntimeException("일치 아이디 없음"));

        if(passwordEncoder.matches(insertVO.getPPw(), mem.getPPw())){
            Map<String,Object> m = new HashMap<>();
            m.put("name",mem.getName());
            String access_token= jwtGenerate.GenerateToken(m, 300);
            String refresh_token= jwtGenerate.GenerateToken(m, 120*60);
            dto = AccessTokenDTO.builder().accesstoken(access_token)
                            .refreshtoken(refresh_token)
                            .name(mem.getName())
                            .build();

        }
        return dto;
    }
}
