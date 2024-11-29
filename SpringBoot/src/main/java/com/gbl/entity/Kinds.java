package com.gbl.entity;

import lombok.*;

/**
 * @Author: Insight
 * @Description: 课程类别--实体类
 * @Date: 2024/5/1 15:29
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Kinds {
    private Integer id;
    private String typeName; //课程类别
    private String parent; //父课程
    private String order; //排序
    private String create_time; //创建时间
}
