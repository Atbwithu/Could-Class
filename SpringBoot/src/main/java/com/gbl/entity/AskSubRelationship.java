package com.gbl.entity;

import lombok.*;

/**
 * @Author: 酋长
 * @Description: 提问和课程关联
 * @Date: 2024/5/25 13:30
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AskSubRelationship {
    private int sid;
    private int aid;
}
