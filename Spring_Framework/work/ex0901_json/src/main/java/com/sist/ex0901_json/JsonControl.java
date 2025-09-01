package com.sist.ex0901_json;

import jsonEx.output.DataVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class JsonControl {

    @RequestMapping("/test")
    public String test(){
        return "ex1";
    }

    @RequestMapping(value = "callTest",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> json(){
        Map<String,Object> map = new HashMap<>();
//        map.put("ename","마동석");
//        map.put("email","ma@koera.com");
//        DataVO dvo = new DataVO("마동석","ma@koera.com");
        DataVO[] vo = new DataVO[3];
        vo[0]=new DataVO("ㅁㅇㄴㄹ","ㅁㄴㅇㄹ");
        vo[1]=new DataVO("1111","2222");
        vo[2]=new DataVO("aaaa","bbbb");
        map.put("ar",vo);
        map.put("length",vo.length);
        return map;
    }
}
