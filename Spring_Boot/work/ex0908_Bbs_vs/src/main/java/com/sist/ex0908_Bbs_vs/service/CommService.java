package com.sist.ex0908_Bbs_vs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.ex0908_Bbs_vs.mapper.CommMapper;
import com.sist.ex0908_Bbs_vs.vo.CommVO;

@Service
public class CommService{
    @Autowired
    private CommMapper commMapper;

    public List<CommVO> commlist(String b_idx){
        return commMapper.list(b_idx);
    }
}
