package com.gbl.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import com.gbl.entity.Permission;
import com.gbl.service.impl.PermissionServiceImpl;
import com.gbl.utils.Auth;
import com.gbl.utils.R;
import com.gbl.utils.ResponseNum;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/Permission")
public class PermissionController {

    @Resource
    PermissionServiceImpl service;

    @Auth
    @PostMapping("/selAll")
    public R<List<Permission>> selAll(@RequestParam("roleId") int roleId) {
        // 使用roleId来查询权限
        List<Permission> permissions = service.selAll(roleId);
        if (permissions == null || permissions.isEmpty()) {
            // 如果没有找到权限，则返回错误
            return R.error(ResponseNum.FAIL);
        }
        // 返回包含权限列表的成功响应
        return R.success(permissions);
    }
}
