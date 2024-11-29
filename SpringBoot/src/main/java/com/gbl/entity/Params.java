package com.gbl.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author: 哥布林酋长（韦忠林）
 * @Description: 分页的查询类
 * @Date: 2024/7/23 20:48
 * @Version: 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Params {
    private String info01; //查询信息1
    private String info02; //查询信息2
    private String info03; //查询信息3
    private int pageNum;
    private int pageSize;
}
