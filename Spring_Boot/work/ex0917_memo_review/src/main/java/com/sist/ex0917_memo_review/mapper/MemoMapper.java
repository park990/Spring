package com.sist.ex0917_memo_review.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sist.ex0917_memo_review.vo.MemoVO;

@Mapper
public interface MemoMapper {
    List<MemoVO> all();
    MemoVO getMemo(String idx);
}
