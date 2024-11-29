package com.gbl.mapper;

import com.gbl.entity.Functions;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: 哥布林酋长
 * @Description: 功能表-接口
 * @Date: 2024/5/1 15:23
 * @Version: 1.0
 */@Mapper
public interface FunctionsMapper {
    //    增 删 改 查
    int insert(Functions functions);
    int delete(int id);
    Functions selectById(int id);
    int update(Functions functions);
    List<Functions> select(Functions functions);

}
