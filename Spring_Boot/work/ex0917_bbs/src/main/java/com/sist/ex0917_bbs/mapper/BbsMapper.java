package com.sist.ex0917_bbs.mapper;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;


import com.sist.ex0917_bbs.vo.BbsVO;

@Mapper
public interface BbsMapper {
    List<BbsVO> list(String bname, String searchType, String searchValue,
    int begin, int end);

    int totalCount(Map<String,Object> map);
    BbsVO get_bbs(String b_idx);
    int add(BbsVO vo);
    int hit(String b_idx);
    int edit(Map<String,Object> map);
    int del(String b_idx);

}
