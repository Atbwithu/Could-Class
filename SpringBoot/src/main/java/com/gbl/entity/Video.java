package com.gbl.entity;

import lombok.*;

/**
 * @Author: Insight
 * @Description: TODO
 * @Date: 2024/7/15 0:48
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Video {
    private int id; //视频id
    private String videoName; //视频名称
    private String savePath; //视频url
    private String originalName; //视频url

    public Video(String videoName, String savePath) {
    }

    public Video(int id) {
        this.id = id;
    }

    public Video(String videoName, String savePath, String originalName) {
        this.videoName = videoName;
        this.savePath = savePath;
        this.originalName = originalName;
    }
}
