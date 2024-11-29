package com.gbl.entity;

import lombok.*;

/**
 * @Author: 酋长
 * @Description: 课程学习记录
 * @Date: 2024/5/28 20:05
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserCourseStudy {
    private Integer id;
    private Integer cid; //课程id
    private int askNum; //提问次数
    private int state; //状态
    private Double learnTime; //
    private Double totalTime; //课程课时
}
