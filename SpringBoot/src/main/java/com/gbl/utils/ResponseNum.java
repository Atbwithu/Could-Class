package com.gbl.utils;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseNum {
    SUCCESS(200,"成功"),

    FAIL(300,"获取数据失败"),

    USER_EX(301,"用户不存在,请重新登录"),

    ERROR(302,"错误请求"),

    account_PASSWORD_ERROR(303,"用户名或密码错误"),

    NO_TOKEN(400,"无token,请重新登录"),

    TOKEN_VERIFY_ERROR(401,"token验证失败,请重新登录"),

    TOKEN_EX(402,"token已过期");

    private final Integer code;

    private final String msg;

}
