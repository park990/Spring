package com.review.ex0919_jaeyoon_jwt_important.domain.member.service;

import org.springframework.stereotype.Service;

import com.review.ex0919_jaeyoon_jwt_important.domain.member.entity.MemEntity;
import com.review.ex0919_jaeyoon_jwt_important.domain.member.repository.MemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemService {    
    private final MemRepository mRepository;
    
    public MemEntity insert(String pid, String pPw, String name){
        MemEntity mem = MemEntity.builder()
                        .pid(pid)
                        .pPw(pPw)
                        .name(name)
                        .build();
        return mRepository.save(mem);
    }
}
