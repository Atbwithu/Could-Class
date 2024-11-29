package com.gbl.entity;

import lombok.*;
/**
 * @Author: Insight
 * @Description: 问答--实体类
 * @Date: 2024/5/1 15:29
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Ask {
    private int id;
    private String userName;    //问答的学生姓名
    private String courseName;     //课程
    private String questions;       //问题
    private long status;       //状态
    private String createTime;      //提问的时间
    private String answer;      //提问的回复
    private int pageNum; //开始页面
    private int pageSize; //展示几条
    private int uid;
}
