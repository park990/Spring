package com.sist.ex0905_deptsearch.service;

import com.sist.ex0905_deptsearch.mapper.DeptMapper;
import com.sist.ex0905_deptsearch.vo.DeptVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DeptService {
    @Autowired
    DeptMapper deptMapper;

    // ALL
    public DeptVO[] getAll() {
        List<DeptVO> list = deptMapper.total();
        DeptVO[] ar=null;
        if (list != null && list.size() > 0) {
            ar = new DeptVO[list.size()];
            list.toArray(ar);
        }
        return ar;
    }

    // Search
    public DeptVO[] getSearch(Map<String,String> m){
        List<DeptVO> list = deptMapper.search(m);
        DeptVO[] ar=null;
        if (list != null && list.size() > 0) {
            ar = new DeptVO[list.size()];
            list.toArray(ar);
        }
        return ar;
    }

}
