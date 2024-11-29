package com.gbl.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 用于保存图片的工具
 */
public class UploadCoverUrl {

    static private String LOCAL_URL_COVER = "D://images";
    static private String URE_IMG = "http://localhost:8080/";

    public String saveCourseUrl(MultipartFile coverFile){
        if(coverFile.isEmpty()){
            return null;
        }
        String dateForm = new SimpleDateFormat("yyyyMMdd").format(new Date());

        String casePath = LOCAL_URL_COVER + dateForm;

        String imgFormat = coverFile.getOriginalFilename().substring(coverFile.getOriginalFilename().lastIndexOf("."));

        File file = new File(casePath);
        if (!file.exists()) {
            file.mkdirs();
        }

        String fileName = UUID.randomUUID().toString() + "." + imgFormat;
        try {
            coverFile.transferTo(new File(casePath + "\\" +fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String urlImg = URE_IMG +"auser/show?fileUrl="+dateForm+"/"+fileName;
        return urlImg;
    }
}
