package com.gbl.mapper;

import com.gbl.entity.Kinds;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: 哥布林酋长
 * @Description: 类型功能接口
 * @Date: 2024/5/1 15:19
 * @Version: 1.0
 */@Mapper
public interface KindsMapper {
//    增 删 改 查
    int insert(Kinds kinds);
    int delete(int id);
    Kinds selectById(int id);
    int update(Kinds kinds);
    List<Kinds> select(Kinds kinds);
}
