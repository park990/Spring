package com.sist.ex0930_docker.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.ex0930_docker.mapper.BbsMapper;
import com.sist.ex0930_docker.vo.BbsVO;

@Service
public class BbsService {
    @Autowired
    private BbsMapper bMapper;

    public int getTotalCount(String bname, String searchType, String searchValue) {
        Map<String, Object> map = new HashMap<>();
        if (bname != null)
            map.put("bname", bname);
        if (searchType != null)
            map.put("searchType", searchType);
        if (searchValue != null)
            map.put("searchValue", searchValue);
        return bMapper.totalCount(map);
    }

    public List<BbsVO> getList(String bname, String searchType, String searchValue,
    int begin, int end){
        return bMapper.list(bname, searchType, searchValue, begin, end);
    }

    public BbsVO get_bbs(String b_idx){
        return bMapper.get_bbs(b_idx);
    }     
    public int addBbs(BbsVO vo){
        return bMapper.add(vo);
    }

   public int getHit(String b_idx){
    return bMapper.hit(b_idx);
   }
}
