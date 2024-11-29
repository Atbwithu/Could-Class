package com.gbl;

import com.github.pagehelper.PageHelper;
import jakarta.servlet.MultipartConfigElement;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.unit.DataSize;

@SpringBootApplication
@EnableCaching
@EnableTransactionManagement
@MapperScan("com.gbl.mapper")
//@ComponentScan(basePackages = {"com.gbl.config"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * 文件上传配置
     *
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement()
    {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个文件最大
        factory.setMaxFileSize(DataSize.parse("5000MB")); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize(DataSize.parse("5000MB"));
        return factory.createMultipartConfig();
    }
    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        return pageHelper;
    }


}