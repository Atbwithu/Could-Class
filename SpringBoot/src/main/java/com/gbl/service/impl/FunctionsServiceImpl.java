package com.gbl.service.impl;

import com.gbl.entity.Functions;
import com.gbl.mapper.FunctionsMapper;
import com.gbl.service.IFunctionsService;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * @Author: Insight
 * @Description: TODO
 * @Date: 2024/5/1 15:39
 * @Version: 1.0
 */
@Service
public class FunctionsServiceImpl implements IFunctionsService {
    @Resource
    FunctionsMapper mapper;
    @Override
    public int insert(Functions functions) {
        return mapper.insert(functions);
    }

    @Override
    public int delete(int id) {
        return mapper.delete(id);
    }

    @Override
    public Functions selectById(int id) {
        return mapper.selectById(id);
    }

    @Override
    public int update(Functions functions) {
        return mapper.update(functions);
    }

    @Override
    public List<Functions> select(Functions functions) {
        return mapper.select(functions);
    }
}
