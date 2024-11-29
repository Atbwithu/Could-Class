package com.gbl.service.impl;

import com.gbl.entity.CarouselInfo;
import com.gbl.mapper.CarouselInfoMapper;
import com.gbl.service.ICarouselInfoService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * @Author: Insight
 * @Description: 轮播图
 * @Date: 2024/5/1 15:38
 * @Version: 1.0
 */
@Service
public class CarouselInfoServiceImpl implements ICarouselInfoService {
    @Resource
    CarouselInfoMapper mapper;
    @Override
    public int insert(CarouselInfo carouselInfo) {
        return 0;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public CarouselInfo selectById(int id) {
        return null;
    }

    @Override
    public int update(CarouselInfo carouselInfo) {
        return mapper.update(carouselInfo);
    }

    @Override
    public List<CarouselInfo> select(CarouselInfo carouselInfo) {
        return null;
    }
}
