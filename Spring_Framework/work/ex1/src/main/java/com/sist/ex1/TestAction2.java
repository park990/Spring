package com.sist.ex1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestAction2{
@RequestMapping("/ex2")
    public ModelAndView execute(){

    ModelAndView mv = new ModelAndView();
    mv.addObject("msg","반갑습니다.SIST");
    //requset.setattribute("msg","반갑습니다.SIST")를 저장한것이다.

    mv.setViewName("ex2");
    return mv;
}

@RequestMapping("/jaeyoon")
public ModelAndView execute2(){
    ModelAndView mv= new ModelAndView();
    mv.addObject("str","jaeyoon");
    mv.setViewName("ex3");
    return mv;
}
}
