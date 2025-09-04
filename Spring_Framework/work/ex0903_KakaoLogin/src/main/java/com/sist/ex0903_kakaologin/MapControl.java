package com.sist.ex0903_kakaologin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MapControl {
    @RequestMapping("/map")
    public String map(){

        return "map";
    }
}
