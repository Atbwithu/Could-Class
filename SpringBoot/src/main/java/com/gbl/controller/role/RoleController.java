package com.gbl.controller.role;




import com.gbl.entity.Role;
import com.gbl.service.RoleService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    RoleService service;

    @RequestMapping("/selectAll")
    public R<List> selectAll(Role role){
//        if (role.getRoleName() != null && !"".equals(role.getRoleName())){
//            role.setRoleName("%"+role.getRoleName()+"%");
//        }
        List<Role> roleList = service.findAll(role);
        return R.success(roleList);
    }

    //新增
    @RequestMapping("/add")
    public int add(@RequestBody Role role){
        return service.insert(role);
    }

    //根据id查询
    @RequestMapping("/selectById")
    public Role selectById(Integer roleId){
        return service.findById(roleId);
    }
    //根据id修改
    @RequestMapping("/updateById")
    public int updateById(Role role){
        return service.update(role);
    }
   //删除
    @RequestMapping("/deleteById")
    public int deleteById(Integer roleId){
        return service.delete(roleId);
    }

}
