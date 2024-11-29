package com.gbl.entity;

import lombok.*;

/**
 * @Author: Insight
 * @Description: TODO
 * @Date: 2024/7/14 13:09
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SomeId {
    private int chapterId; //用于删除课程时删除课程下所以章节的id
    private int videoId; //用于删除章节时删除视频下所以章节的id
    private int childrenId; //用于删除父分类查询子分类的id

}
