package com.gbl.service;

import com.gbl.entity.Kinds;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: 哥布林酋长
 * @Description: 类型功能接口
 * @Date: 2024/5/1 15:19
 * @Version: 1.0
 */
public interface IKindsService {
    @Transactional
//    增 删 改 查
    int insert(Kinds kinds);
    @Transactional
    int delete(int id);
    Kinds selectById(int id);
    @Transactional
    int update(Kinds kinds);
    List<Kinds> select(Kinds kinds);
}
