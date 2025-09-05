package com.sist.ex0905_dept.service;

import com.sist.ex0905_dept.mapper.DeptMapper;
import com.sist.ex0905_dept.vo.DeptVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptService {
    @Autowired
    private DeptMapper DeptMapper;

    public DeptVO[] getTotal(){
        List<DeptVO> list= DeptMapper.total();
        DeptVO[] ar =null;
        if(list!=null&&list.size()>0){
            ar= new DeptVO[list.size()];
            list.toArray(ar);
        }
        return ar;
    }
}
