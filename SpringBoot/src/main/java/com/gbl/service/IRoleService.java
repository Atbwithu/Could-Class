package com.gbl.service;

import com.gbl.entity.Role;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: Insight
 * @Description: 角色-接口
 * @Date: 2024/5/1 15:24
 * @Version: 1.0
 */
public interface IRoleService {
    //    增 删 改 查
    @Transactional
    int insert(Role role);
    @Transactional
    int delete(int id);
    Role selectById(int id);
    @Transactional
    int update(Role role);
    List<Role> select(Role role);
}
