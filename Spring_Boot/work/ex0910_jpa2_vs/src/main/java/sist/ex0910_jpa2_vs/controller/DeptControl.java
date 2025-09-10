package sist.ex0910_jpa2_vs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sist.ex0910_jpa2_vs.service.DeptService;

@RestController
@RequestMapping("/dept")
public class DeptControl {
    @Autowired
    private DeptService deptService;

    @RequestMapping("/all")
    public Object all(){
        return deptService.findAll();
    }

    @RequestMapping("/findDept")
    public Object dept(@RequestParam Long deptno){
        return deptService.findByDeptno(deptno);
    }
}
