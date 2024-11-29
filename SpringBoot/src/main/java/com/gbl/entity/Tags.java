package com.gbl.entity;

import lombok.*;

/**
 * @Author: 酋长
 * @Description: 标记类
 * @Date: 2024/6/1 16:30
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Tags {
    private int userId;
    private int courseId;
}
