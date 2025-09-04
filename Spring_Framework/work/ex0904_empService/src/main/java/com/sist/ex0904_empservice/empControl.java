package com.sist.ex0904_empservice;

import mybatis.service.empMapper;
import mybatis.vo.EmpVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class empControl {
    @Autowired
    empMapper empMapper;


    @RequestMapping("/emp")
    public String empall(Model model){

        EmpVO[] ar =empMapper.all();
        System.out.println("adsasdasd"+ar.length);
        model.addAttribute("ar",ar);
        return "emp";
    }

    @PostMapping("/search")
    public ModelAndView search(String type, String value){
        ModelAndView mv = new ModelAndView();
        System.out.println(type+value);
        EmpVO[] ar = empMapper.search(type,value);
        mv.addObject("ar",ar);
        mv.setViewName("search");
        return mv;
    }
}
