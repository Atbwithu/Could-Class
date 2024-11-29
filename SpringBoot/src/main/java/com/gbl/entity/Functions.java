package com.gbl.entity;

import lombok.*;
/**
 * @Author: Insight
 * @Description: 功能表--实体类
 * @Date: 2024/5/1 15:29
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Functions {
    private Integer id;
    private String functionName; // 功能名称
    private String parent; // 父功能
    private String reqUrl; // 访问路径
    private String sort; // 排序
}
