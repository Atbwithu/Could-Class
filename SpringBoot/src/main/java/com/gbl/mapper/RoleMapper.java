package com.gbl.mapper;


import com.gbl.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {
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
