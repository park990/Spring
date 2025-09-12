package com.sist.ex0911_jwt.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sist.ex0911_jwt.jwt.JwtProvider;
import com.sist.ex0911_jwt.repository.MemberRepository;
import com.sist.ex0911_jwt.vo.MemVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemService {
    private final MemberRepository mRepository;
    private final JwtProvider jwtProvider;

    public String makeToken(String mid) {
        MemVO mvo = null;
        String token = null;
        Optional<MemVO> vo = mRepository.findBymId(mid);

        if (vo.isPresent()) {
            mvo = vo.get();
            Map<String, Object> map = new HashMap<>();
            map.put("mIdx", mvo.getMIdx());
            map.put("mId", mvo.getMId());
            map.put("mName", mvo.getMName());
            map.put("mIdx", mvo.getMIdx());
            token = jwtProvider.genToken(map, 60);
        }

        return token;
    }
}
