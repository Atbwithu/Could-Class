package com.gbl.entity;

import lombok.*;
/**
 * @Author: Insight
 * @Description: 轮播图表--实体类
 * @Date: 2024/5/1 15:29
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CarouselInfo {
    private Integer id;
    private String page;  //归属页面
    private String imgUrl;  //图片地址
    private String linkUrl;  //链接地址

}
