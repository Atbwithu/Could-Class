package com.gbl.service.impl;

import com.gbl.entity.Kinds;
import com.gbl.mapper.KindsMapper;
import com.gbl.service.IKindsService;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * @Author: Insight
 * @Description: TODO
 * @Date: 2024/5/1 15:42
 * @Version: 1.0
 */
@Service
public class KindServiceImpl implements IKindsService {
    @Resource
    KindsMapper mapper;
    @Override
    public int insert(Kinds kinds) {
        return mapper.insert(kinds);
    }

    @Override
    public int delete(int id) {
        return mapper.delete(id);
    }

    @Override
    public Kinds selectById(int id) {
        return mapper.selectById(id);
    }

    @Override
    public int update(Kinds kinds) {
        return mapper.update(kinds);
    }

    @Override
    public List<Kinds> select(Kinds kinds) {
        return mapper.select(kinds);
    }
}
