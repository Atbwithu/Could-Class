package com.gbl.service;

import com.gbl.entity.CarouselInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: 哥布林酋长
 * @Description: 轮播-接口
 * @Date: 2024/5/1 15:23
 * @Version: 1.0
 */
public interface ICarouselInfoService {
    //    增 删 改 查
    @Transactional
    int insert(CarouselInfo carouselInfo);
    @Transactional
    int delete(int id);
    CarouselInfo selectById(int id);
    @Transactional
    int update(CarouselInfo carouselInfo);
    List<CarouselInfo> select(CarouselInfo carouselInfo);

}
