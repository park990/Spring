package com.sist.ex0930_compose.mybatis.service;

import com.sist.ex0930_compose.mybatis.mapper.EmpMapper;
import com.sist.ex0930_compose.mybatis.vo.EmpVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpService {
    // 필요한 맵퍼들 지정
    @Autowired
    private EmpMapper EmpMapper;

    public EmpVO[] getAll(){
        EmpVO[] ar = null;
        List<EmpVO> list= EmpMapper.all();
        if(list!=null&&list.size()>0){
            ar= new EmpVO[list.size()];
            list.toArray(ar);
        }
        return ar;
    }
}
