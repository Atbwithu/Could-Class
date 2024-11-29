package com.gbl.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author: 章节的学习记录
 * @Description: 哥布林酋长（韦忠林）
 * @Date: 2024/7/27 16:27
 * @Version: 1.0
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChapterStudy {
    private int id;
    private int askNum; //提问次数
    private int learnNum; //学生学习的次数

    public ChapterStudy(int askNum, int learnNum) {
        this.askNum = askNum;
        this.learnNum = learnNum;
    }
}
