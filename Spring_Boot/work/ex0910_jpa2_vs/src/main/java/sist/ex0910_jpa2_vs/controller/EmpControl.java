package sist.ex0910_jpa2_vs.controller;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import sist.ex0910_jpa2_vs.service.EmpService;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequiredArgsConstructor
@RequestMapping("/emp")
public class EmpControl {
   private final EmpService empService;

    @GetMapping("/all")
    public Object findAll(){

        return empService.findAll();
    }

    @GetMapping("/findEmpno")
    public Object findEmpno(@RequestParam("empno") Long empno){
      
        return empService.findByEmp(empno);
    }

    //직종과 부서검색을 and 조건으로 검색하자.

    // @RequestMapping("/jobAndDept")
    @GetMapping("/jobAndDept")
    public Object getMethod(@RequestParam String job, @RequestParam String deptno){
        return empService.jobAndDeptno(job, deptno);
    }

    @GetMapping("/jobContainDept")
    public Object getContainMethod(@RequestParam String job, @RequestParam String deptno){
        return empService.jobLikeDeptno(job, deptno);
    }

    @GetMapping("/jobLikeDept")
    public Object getLikeMethod2(@RequestParam String job,@RequestParam String deptno){
        return empService.findByJobLikeAndDeptno(job, deptno);
    }

    @GetMapping("/startWith")
    public Object startwith(@RequestParam String ename){
        return empService.StartWithEname(ename);
    }

    @GetMapping("/salAndDate")
    public Object salAndDate(@RequestParam BigDecimal sal){
        return empService.findBySalLessThanEqualOrderByHiredateDesc(sal);
    }


    
}
