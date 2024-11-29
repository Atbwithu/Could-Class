package com.gbl.config;

import com.gbl.Interceptor.CrossInterceptorHandler;
import com.gbl.Interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${video.upload.path}")
    private String uploadPath;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //InterceptorRegistry 这是一个注册器
        registry.addInterceptor(new CrossInterceptorHandler()).addPathPatterns(new String[] {"/**"});//这是匹配路径  excludePathPatterns这个可以排除路径
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/userInfo/login", "/error/**");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (uploadPath!= null) {
            registry.addResourceHandler("/video/**").addResourceLocations("file:///E:/learn_Java/wei/SSMProject/GBLClassSpringBoot/KuYunClass:");
        } else {
            // 可以添加一些日志记录或者抛出异常，提示配置路径为空
            System.out.println("视频上传路径未配置或配置错误");
        }
    }
}