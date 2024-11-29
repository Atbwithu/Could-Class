package com.gbl.Interceptor;


import com.gbl.utils.Auth;
import com.gbl.utils.ToKenUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            if (handlerMethod.getBean() instanceof BasicErrorController) {
                //放行
                return true;
            }
            val auth = handlerMethod.getMethod().getAnnotation(Auth.class);
            if (auth != null && auth.require()) {
                String token = request.getHeader("token");
                if (StringUtils.isNoneBlank(token)) {
                    //to do list 校验token
                    if (ToKenUtil.verifyToKen(token)){
                        return true;
                    }else {
                        request.getRequestDispatcher("/error/tokenError").forward(request,response);
                    }
                }else {
                    request.getRequestDispatcher("/error/token").forward(request,response);
                }
            }else {
                /*条件不成立放行*/
                return true;
            }
        }else {
            return true;
        }
        return false;
    }
}
