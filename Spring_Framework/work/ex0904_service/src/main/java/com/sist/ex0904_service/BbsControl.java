package com.sist.ex0904_service;

import mybatis.dao.BbsDAO;
import mybatis.service.BbsMapper;
import mybatis.vo.BbsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class BbsControl {

    @Autowired
    private BbsMapper bbsMapper;

    @RequestMapping("/list")
    public String list(Model model){
        BbsVO[] ar = bbsMapper.list("BBS",1,10);
        model.addAttribute("ar",ar);
        return "list";
    }
}
