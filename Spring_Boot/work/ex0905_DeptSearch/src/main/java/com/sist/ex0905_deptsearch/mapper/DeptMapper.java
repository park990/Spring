package com.sist.ex0905_deptsearch.mapper;

import com.sist.ex0905_deptsearch.vo.DeptVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DeptMapper {
    List<DeptVO> total();

    List<DeptVO> search(Map<String,String> m);
}
