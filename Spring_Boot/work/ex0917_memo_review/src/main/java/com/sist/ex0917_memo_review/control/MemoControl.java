package com.sist.ex0917_memo_review.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.ex0917_memo_review.service.Memoservice;
import com.sist.ex0917_memo_review.vo.MemoVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/memo")
@RequiredArgsConstructor
public class MemoControl {
    private final Memoservice service;

    @RequestMapping("/all")
    public Map<String, Object> all(){
        Map<String, Object> m = new HashMap<>();
        List<MemoVO> list = service.getAll();
        m.put("m_list",list);
        return m;
    }

    @RequestMapping("/getMemo")
    public Map<String,Object> getMemo(String idx){
        System.out.println("asdfsdafsdafdfaafa::::"+idx);
        Map<String,Object> m = new HashMap<>();
        MemoVO vo = service.getMemo(idx);
        m.put("vo",vo);
        return m;
    }
}
