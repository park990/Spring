package com.sist.ex0908_Bbs_vs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sist.ex0908_Bbs_vs.vo.BbsVO;

@Mapper
public interface BbsMapper {
    int totalCount(String bname); 

    List<BbsVO> list(String bname, int begin, int end);

    int add(BbsVO vo);
    
    BbsVO see(String b_idx);

    int hit(String b_idx);
    int udt(BbsVO vo);
}
