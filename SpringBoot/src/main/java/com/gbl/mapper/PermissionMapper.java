package com.gbl.mapper;


import com.gbl.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermissionMapper {

    //查询全部功能
    List<Permission> selAll(int roleId);
}
