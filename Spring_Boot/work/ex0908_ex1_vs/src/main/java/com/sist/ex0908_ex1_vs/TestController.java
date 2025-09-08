package com.sist.ex0908_ex1_vs;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class TestController {

    @GetMapping("/t1")
    public String test1() {
        return "연습1입니다";
    }
    
     @GetMapping("/t2")
    public String test2() {
        return "HelloBoot";
    }
 
    
}