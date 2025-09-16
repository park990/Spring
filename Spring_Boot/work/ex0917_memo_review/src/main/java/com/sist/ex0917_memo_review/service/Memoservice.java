package com.sist.ex0917_memo_review.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sist.ex0917_memo_review.mapper.MemoMapper;
import com.sist.ex0917_memo_review.vo.MemoVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Memoservice {

    private final MemoMapper mapper;

    public List<MemoVO> getAll(){
        return mapper.all();
    }
    public MemoVO getMemo(String idx){
        return mapper.getMemo(idx);
    }
}
