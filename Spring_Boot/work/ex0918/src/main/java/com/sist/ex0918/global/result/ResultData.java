package com.sist.ex0918.global.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter@Getter@AllArgsConstructor
public class ResultData<T> {
    private int totalCount;
    private String msg;
    private T data;

    public static <T> ResultData<T> jsonReturn (int totalCount, String msg, T data){
        return new ResultData<>(totalCount, msg, data);
    }
    
    public static <T> ResultData<T> jsonReturn (int totalCount, String msg){
        return jsonReturn(totalCount, msg, null);
    }
}
