package com.gbl.entity;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Chapter {
    private int id;
    private String name; //章节名
    private String courseName; //课程名
    private int askNum; //提问次数
    private int learnNUm; //学习次数
    private String createTime; //创建时间
    private String videoImg; //视频地址路径
    private String videoName; //视频名称

}
