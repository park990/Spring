package com.sist.ex0929_docker.control;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestControl {

    @RequestMapping("/t1")
    public Map<String, Object> test1(@RequestParam String param) {
        Map<String, Object> m = new HashMap<>();
        m.put("msg", "도커연숩삽솝");
        m.put("str", param);

        return m;
    }
}
