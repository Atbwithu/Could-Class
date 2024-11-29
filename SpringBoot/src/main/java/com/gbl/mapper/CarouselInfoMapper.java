package com.gbl.mapper;

import com.gbl.entity.CarouselInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: 哥布林酋长
 * @Description: 轮播-接口
 * @Date: 2024/5/1 15:23
 * @Version: 1.0
 */
@Mapper
public interface CarouselInfoMapper {
    //    增 删 改 查
    int insert(CarouselInfo carouselInfo);
    int delete(int id);
    CarouselInfo selectById(int id);
    int update(CarouselInfo carouselInfo);
    List<CarouselInfo> select(CarouselInfo carouselInfo);

}
