package com.sist.ex0904;

import data.vo.DataVO;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class TestControl {
    @GetMapping("/index")
    public ModelAndView index(String code) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("code", code);
        mv.setViewName("index");
        return mv;
    }

    @GetMapping("/test")
    public ModelAndView main(String code) {
        ModelAndView mv= new ModelAndView();
        if(code!=null){
            DataVO vo = new DataVO();
            vo.setStr(code);
            mv.addObject("vo",vo);
            mv.setViewName("test");
        }
        return mv;
    }
}
