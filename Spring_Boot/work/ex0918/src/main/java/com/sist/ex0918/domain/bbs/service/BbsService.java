package com.sist.ex0918.domain.bbs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sist.ex0918.domain.bbs.entity.Bbs;
import com.sist.ex0918.domain.bbs.repository.BbsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BbsService {
    private final BbsRepository bbsRepository;

    public List<Bbs> getList() {
        return bbsRepository.findAll();
    }

    public Bbs getBbs(Long b_idx) {
        Bbs bbs = null;
        Optional<Bbs> opt = bbsRepository.findById(b_idx);
        if (!opt.isEmpty()) {
            bbs = opt.get();
        }
        return bbs;
    }

    public Bbs create(String title, String writer, String content){
        Bbs bbs = Bbs.builder()
        .title(title)
        .writer(writer)
        .content(content)
        .build();
        return bbsRepository.save(bbs);
    }
}
