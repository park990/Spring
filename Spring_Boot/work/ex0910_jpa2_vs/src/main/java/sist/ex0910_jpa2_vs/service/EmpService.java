package sist.ex0910_jpa2_vs.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sist.ex0910_jpa2_vs.repository.EmpRepository;
import sist.ex0910_jpa2_vs.store.Emp;

@Service
public class EmpService {
    @Autowired
    EmpRepository empRepository;

    public List<Emp> findAll(){
        return empRepository.findAll();
    }

    public Optional<Emp> findByEmp(Long empno){
        return empRepository.findByEmpno(empno);
    }

    public List<Emp> findByDeptno(String deptno){
        return empRepository.findByDeptno(deptno);
    }

    public List<Emp> jobAndDeptno(String job, String deptno){
        return empRepository.findByJobAndDeptno(job, deptno);
    }


    public List<Emp> findByJobLikeAndDeptno(String job, String deptno){
        return empRepository.findByJobLikeAndDeptno(job, deptno);
    }



    public List<Emp> jobLikeDeptno(String job, String deptno){
        return empRepository.findByJobContainingAndDeptno(job, deptno);
    }
    public List<Emp> StartWithEname(String ename){
        return empRepository.findByEnameStartingWith(ename);
    }

    public List<Emp> findBySalLessThanEqualOrderByHiredateDesc(BigDecimal sal){
        return empRepository.findBySalLessThanEqualOrderByHiredateDesc(sal);
    }
}
