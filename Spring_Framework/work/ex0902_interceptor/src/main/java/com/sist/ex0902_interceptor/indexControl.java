package com.sist.ex0902_interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexControl {
    @RequestMapping("/")
    public String index(){

        return "index"; // view/index.jsp로 forward됨
    }
}
