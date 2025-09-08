package com.sist.ex0908_Bbs_vs.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class indexControl {

    @GetMapping("/")
    public String index(){
        return "index";
    }
    
}
