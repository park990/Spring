package com.sist.ex0930_compose.mybatis.mapper;

import com.sist.ex0930_compose.mybatis.vo.EmpVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface EmpMapper {
    // SQL문을 가진 mapper파일(emp.xml)과 연동된다.
    // 그래서 여기에 정의 하는 함수들은 emp.xml에 존재하는 id 명과 동일해야 한다.
    List<EmpVO> all();
}
