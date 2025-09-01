package com.sist.ex0901_json;

import jsonEx.output.empVO;
import mybatis.dao.empDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class empControl {

    @Autowired
    empDAO empDAO;

    @RequestMapping(value = "/emp",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> emp(){
        empVO[] ar = empDAO.all();
        System.out.println(ar.length);
        Map<String,Object> m = new HashMap<>();
        m.put("ar",ar);
        m.put("length",ar.length);
        return m;
    }
}
