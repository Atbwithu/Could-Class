package com.gbl.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author: 哥布林酋长
 * @Description: 课程分类
 * @Date: 2024/5/9 15:43
 * @Version: 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryChildren {
    private int id;
    private String cname; //类别名
    private String father; //所属父类
    private Integer sort; //排序
    private String createTime; //创建时间

    public CategoryChildren(String cname, Integer sort, String createTime) {
        this.cname = cname;
        this.sort = sort;
        this.createTime = createTime;
    }
}
