package com.gbl.Interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class CrossInterceptorHandler implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        response.setHeader("Access-Control-Allow-Origin", "*");//设定响应头，比如说前端的IP
        response.setHeader("Access-Control-Allow-Credentials", "true");//允许传递Cookie
        response.setHeader("Access-Control-Allow-Methods", "POST, GET , PUT , OPTIONS");//提交的方法设置
        response.setHeader("Access-Control-Max-Age", "3600");//设置过期时间。这里是一小时
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,accept,authorization,content-type,token");//允许的请求头
        //放行
        return true;
    }
}