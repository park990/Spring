package com.sist.ex0916_memo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.ex0916_memo.service.MemoService;
import com.sist.ex0916_memo.vo.MemoVO;

@RestController
@RequestMapping("/memo")
public class MemoControl {
    @Autowired
    private MemoService service;

    @RequestMapping("/all")
    public Map<String,Object> getAll(){
        Map<String, Object> m = new HashMap<>();
        List<MemoVO> list = service.getAll();
        m.put("m_list", list);
        m.put("length", list.size());
        return m;
    }

    @RequestMapping("/{idx}")
    public Map<String,Object> getMemo(@PathVariable String idx){
        Map<String, Object> m = new HashMap<>();
        System.out.println(idx);
        MemoVO vo = service.getMemo(idx);
        m.put("vo",vo);
        return m;
    }

}
