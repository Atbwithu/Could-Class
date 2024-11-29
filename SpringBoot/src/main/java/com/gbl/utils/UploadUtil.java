package com.gbl.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public class UploadUtil {
    //获取阿里域名
    public static final String ALI_DOMAIN = "https://gebulin.oss-cn-guangzhou.aliyuncs.com/";

    public static String uploadImage(MultipartFile file) throws IOException {
        //生成文件命
        String originalFilename = file.getOriginalFilename();
        String sxt = "."+ FilenameUtils.getExtension(originalFilename);
        String uuid = UUID.randomUUID().toString().replace("-","");
        String fileName = uuid+sxt;
        String key = "subject/imgs/"+fileName;
        //地域节点
        String enpoint = "http://oss-cn-guangzhou.aliyuncs.com";
        String accesKeyId = "LTAI5tM4YRj8q5pHm2wydHqL"; //阿里云AccessKey获取
        String accesKeySecret = "55aw9pjuFIWZv8K6S2D1oIPcgTl1EM";//阿里云AccessKey获取
        //ODD客户端对象
        OSS ossClient = new OSSClientBuilder().build(enpoint,accesKeyId,accesKeySecret);
        ossClient.putObject("gebulin",//仓库名
                key,//文件名
                 file.getInputStream());
        ossClient.shutdown();
        String  url = ALI_DOMAIN+"subject/imgs/"+fileName;
        return url;
    }
    //获取阿里域名
    public static String uploadVideo(MultipartFile file) throws IOException {
        //生成文件命
        String originalFilename = file.getOriginalFilename();
        String sxt = "."+ FilenameUtils.getExtension(originalFilename);
        String uuid = UUID.randomUUID().toString().replace("-","");
        String fileName = uuid+sxt;
        String key = "chapter/"+fileName;
        //地域节点
        String enpoint = "http://oss-cn-guangzhou.aliyuncs.com";
        String accesKeyId = "LTAI5tM4YRj8q5pHm2wydHqL"; //阿里云AccessKey获取
        String accesKeySecret = "55aw9pjuFIWZv8K6S2D1oIPcgTl1EM";//阿里云AccessKey获取
        //ODD客户端对象
        OSS ossClient = new OSSClientBuilder().build(enpoint,accesKeyId,accesKeySecret);
        ossClient.putObject("gebulin",//仓库名
                key,//文件名
                file.getInputStream());
        ossClient.shutdown();
        String  url = ALI_DOMAIN+"chapter/"+fileName;
        return url;
    }

}
