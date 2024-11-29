package com.gbl.entity;

import lombok.*;

/**
 * @Author: 酋长
 * @Description: 学习记录实体类
 * @Date: 2024/5/1 15:29
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Study {
    private Integer id;
    private String userName; //名字
    private String phone; //手机号
    private String sex; //性别
    private Integer learnNum; //学习次数
    private Integer collectNum; //收藏课程数
    private Integer questionNum; //提问课程数
    private Double learnTime; //学习时长

    public Study(Integer learnNum, Integer collectNum, Integer questionNum, Double learnTime) {
        this.learnNum = learnNum;
        this.collectNum = collectNum;
        this.questionNum = questionNum;
        this.learnTime = learnTime;
    }

    public Study(Integer questionNum, Double learnTime) {
        this.questionNum = questionNum;
        this.learnTime = learnTime;
    }
}
