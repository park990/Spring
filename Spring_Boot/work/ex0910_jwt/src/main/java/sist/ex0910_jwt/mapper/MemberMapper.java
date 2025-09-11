package sist.ex0910_jwt.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

import sist.ex0910_jwt.vo.MemVO;

@Mapper
public interface MemberMapper {

    int reg(MemVO vo);

    MemVO login(String m_id);
}
