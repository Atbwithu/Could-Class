package com.gbl.service.impl;


import com.gbl.entity.Role;
import com.gbl.mapper.RoleMapper;
import com.gbl.service.RoleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    RoleMapper mapper;
    @Override
    public List<Role> findAll(Role role) {
        return mapper.findAll(role);
    }

    @Override
    public Role findById(Integer id) {
        return mapper.findById(id);
    }

    @Override
    public int insert(Role role) {
        return mapper.insert(role);
    }

    @Override
    public int delete(Integer id) {
        return mapper.delete(id);
    }

    @Override
    public int update(Role role) {
        return mapper.update(role);
    }
}
