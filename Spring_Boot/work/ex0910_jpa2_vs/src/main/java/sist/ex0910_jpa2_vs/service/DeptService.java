package sist.ex0910_jpa2_vs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import sist.ex0910_jpa2_vs.repository.DeptRepository;

@Service
@RequiredArgsConstructor
public class DeptService {
    private final DeptRepository deptRepository;

    public Object findAll(){
        return deptRepository.findAll();
    }

    public Object findByDeptno(Long deptno){
        return deptRepository.findByDeptno(deptno);
    }
}
