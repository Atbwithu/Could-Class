package com.gbl.utils;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class R<T> implements Serializable {
    @Serial
    private static final long serialVersionUID=1L;
    private Integer code;
    private String msg;
    private T data;

    private R() {
    }

    public static <T> R<T>  success(T data){
        R<T> response = new R<>();
        response.setCode(ResponseNum.SUCCESS.getCode());
        response.setMsg(ResponseNum.SUCCESS.getMsg());
        response.setData(data);
        return response;
    }

    public static <T> R<T>  error(ResponseNum responseNum){
        R<T> response = new R<>();
        response.setCode(responseNum.getCode());
        response.setMsg(responseNum.getMsg());
        return response;
    }

    public static <T> R<T> error(Integer errorCode,String errorMsg){
        R<T> response = new R<>();
        response.setCode(errorCode);
        response.setMsg(errorMsg);
        return response;
    }
}
