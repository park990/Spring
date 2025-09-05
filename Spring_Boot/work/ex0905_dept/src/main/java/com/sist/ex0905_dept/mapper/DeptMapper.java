package com.sist.ex0905_dept.mapper;

import com.sist.ex0905_dept.vo.DeptVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeptMapper {
    //dept.xml과 연동되는 객체다.
    List<DeptVO> total();
}
