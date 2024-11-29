package com.gbl.service.role;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gbl.entity.Role;
import com.gbl.mapper.RoleMapper;
import org.springframework.stereotype.Service;
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
