package com.sist.ex0918.domain.controller;

import java.nio.file.PathMatcher;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.ex0918.domain.bbs.entity.Bbs;
import com.sist.ex0918.domain.bbs.entity.service.BbsService;
import com.sist.ex0918.global.result.ResultData;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bbs")
public class ApiBbsControl {
    private final BbsService bbsService;

    @GetMapping("")
    public ResultData<List<Bbs>> getList(){
        List<Bbs> list=  bbsService.getList();
        String msg = "fail";
        if(list !=null && list.size()>0)
            msg="success";
        
        return ResultData.jsonReturn(list.size(),msg,list);
    }

    @GetMapping("/{b_idx}")
    public ResultData<Bbs> getBbs(@PathVariable("b_idx") Long b_idx ){
        Bbs bbs = bbsService.getBbs(b_idx);
        String msg = "fail";
        if(bbs!=null)
            msg="success";

        return ResultData.jsonReturn(1, msg, bbs);
    }
}
