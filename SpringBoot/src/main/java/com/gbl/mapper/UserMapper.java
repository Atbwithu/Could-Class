package com.gbl.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gbl.entity.User;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: 哥布林酋长
 * @Description: 用户接口
 * @Date: 2024/5/1 15:19
 * @Version: 1.0
 */@Mapper
public interface UserMapper extends BaseMapper<User> {
}
