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
public class Category {
    private int id;
    private String name;
    private int parent;
    private Integer sort;
    private String createTime;



    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parent='" + parent + '\'' +
                ", sort='" + sort + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
