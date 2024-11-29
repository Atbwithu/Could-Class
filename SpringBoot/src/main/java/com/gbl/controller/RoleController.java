package com.gbl.controller;




import com.gbl.entity.Role;
import com.gbl.service.RoleService;
import com.gbl.utils.Auth;
import com.gbl.utils.R;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    RoleService service;

    @Auth
    @RequestMapping("/selectAll")
    public R<List> selectAll(Role role){
//        if (role.getRoleName() != null && !"".equals(role.getRoleName())){
//            role.setRoleName("%"+role.getRoleName()+"%");
//        }
        List<Role> roleList = service.findAll(role);
        return R.success(roleList);
    }

    //新增
    @Auth
    @RequestMapping("/add")
    public int add(@RequestBody Role role){
        int insert = service.insert(role);
        return insert;
    }

    //根据id查询
    @Auth
    @RequestMapping("/selectById")
    public Role selectById(Integer roleId){
        return service.findById(roleId);
    }
    //根据id修改
    @Auth
    @RequestMapping("/updateById")
    public int updateById(Role role){
        return service.update(role);
    }
   //删除
    @Auth
    @RequestMapping("/deleteById")
    public int deleteById(Integer roleId){
        return service.delete(roleId);
    }

}
