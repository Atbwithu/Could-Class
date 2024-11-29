package com.gbl.service;


import com.gbl.entity.Role;

import java.util.List;

public interface RoleService {
    //查询全部
    List<Role> findAll(Role role);
    //根据id查询
    Role findById(Integer id);
    //新增
    int insert(Role role);
    //删除
    int delete(Integer id);
    //根据id修改
    int update(Role role);
}
