package com.gbl.service;

import com.gbl.entity.Functions;

import java.util.List;

/**
 * @Author: 哥布林酋长
 * @Description: 功能表-接口
 * @Date: 2024/5/1 15:23
 * @Version: 1.0
 */
public interface IFunctionsService {
    //    增 删 改 查
    int insert(Functions functions);
    int delete(int id);
    Functions selectById(int id);
    int update(Functions functions);
    List<Functions> select(Functions functions);

}
