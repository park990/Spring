package com.sist.ex0905_deptsearch.Controller;

import com.sist.ex0905_deptsearch.service.DeptService;
import com.sist.ex0905_deptsearch.vo.DeptVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
public class DeptControl {
    @Autowired
    DeptService deptService;

    @GetMapping("/total")
    public ModelAndView total() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("ar", deptService.getAll());
        mv.setViewName("dept");
        return mv;
    }

    @PostMapping("/search")
    @ResponseBody
    public Map<String, Object> search(String type, String value) {
        System.out.println(type);
        System.out.println(value);

        Map<String, String> xmlMap = new HashMap<>();
        xmlMap.put("type", type);
        xmlMap.put("value", value);
        DeptVO[] ar = deptService.getSearch(xmlMap);
        System.out.println(ar.length);


        Map<String,Object> jsonMap=null;
        if (ar != null && ar.length > 0) {
            jsonMap= new HashMap<>();
            jsonMap.put("ar",ar);
            jsonMap.put("length",ar.length);
        }

        return jsonMap;
    }


}
