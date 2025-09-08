package com.sist.ex0908_Bbs_vs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sist.ex0908_Bbs_vs.vo.CommVO;

@Mapper
public interface CommMapper {
    List<CommVO> list(String b_idx);
    
}
