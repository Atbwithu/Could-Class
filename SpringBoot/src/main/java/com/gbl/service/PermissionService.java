package com.gbl.service;



import com.gbl.entity.Permission;

import java.util.List;

public interface PermissionService {
    //查询全部功能
    List<Permission> selAll(int roleId);
}
