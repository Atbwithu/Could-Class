package com.gbl.service.impl;


import com.gbl.entity.Permission;
import com.gbl.mapper.PermissionMapper;
import com.gbl.service.PermissionService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    PermissionMapper mapper;
    @Override
    public List<Permission> selAll(int roleId) {
        return mapper.selAll(roleId);
    }
}
