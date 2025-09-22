package com.review.ex0919_jaeyoon_jwt_important.global.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter@AllArgsConstructor
public class ResultData<T> {
    private int totalCount;
    private String msg;
    private T data;
    
    public static <T> ResultData<T> JsonReturn(int totalCount, String msg, T data){
        return new ResultData<T> (totalCount,msg,data);
    }

    public static <T> ResultData<T> JsonReturn(int totalCount, String msg){
        return ResultData.JsonReturn(totalCount, msg,null);
    }
}
