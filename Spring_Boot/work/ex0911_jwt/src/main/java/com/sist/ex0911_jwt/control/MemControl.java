package com.sist.ex0911_jwt.control;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sist.ex0911_jwt.service.MemService;

@RestController
public class MemControl {
    @Autowired
    private MemService mService;

    @GetMapping("/code")
        public Map<String,Object> code(@RequestParam("mId") String mId){
            Map<String,Object> result= new HashMap<>();

            String token = mService.makeToken(mId);
            result.put("access_token", token);
            return result;
        }
    
}
