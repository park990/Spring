package com.sist.ex0905_ex1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class Test1Control {

    //application.properties파일에 있는 변수값을 가져온다.
    @Value("${msg}")
    private String message;


    @GetMapping("/")
    public String index(){
        return "SpringBoot Ex1";
    }

    @GetMapping("/t1")
    //@RequestParam이 있으면 param값을 주지 않으면 실행 불가.
    public String test1(@RequestParam String param){
        return "방가방가 -param:"+param;
    }

    @GetMapping("/t2/{var}")
    public String test2(@PathVariable() String var){
        return "경로변수"+var;
    }
    @GetMapping("/t3/{var}")
    public String test3(@PathVariable("var") String param){
        return "경로변수"+param;
    }

    @GetMapping("/t4")
    public ModelAndView test4(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("str", "마마모모미미");
        mv.addObject("msg",message);

        mv.setViewName("t4");
        return mv;
    }

}
