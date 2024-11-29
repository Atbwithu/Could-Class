package com.gbl.entity;

import lombok.*;

/**
 * @Author: 酋长
 * @Description: 总学习记录表
 * @Date: 2024/5/28 20:05
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserStudyCourse {
    private Integer id;
    private String userName; //学员名称
    private String courseName; //课程名
    private String courseTime; //课程课时
    private Double learnTime; //学习时长
    private Integer askNum; //提问课程数
    private Double finishTime; //完成率
}
