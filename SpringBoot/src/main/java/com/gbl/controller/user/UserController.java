package com.gbl.controller.user;

import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gbl.entity.*;
import com.gbl.service.study.IStudyService;
import com.gbl.service.user.IUserService;
import com.gbl.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService service;
    @Autowired
    StringRedisTemplate redis;

    @Resource
    IStudyService serviceStudy;
    @RequestMapping("/list")
    public ResponseResult select(User user){
        return ResponseResult.success(service.list());
    }
    @RequestMapping("/delete")
    public ResponseResult delete(int id){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        return ResponseResult.success(service.remove(wrapper));
    }
    @RequestMapping("/selectById")
    public ResponseResult selectById(int id){
        return ResponseResult.success(service.getById(id));
    }

    @PostMapping("/login")
    public ResponseResult login(String username, String password){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        User user = service.getOne(wrapper.eq("username", username));
        if (user == null) {
            return ResponseResult.fail("用户不存在");
        }
        boolean checkpw = BCrypt.checkpw(password, user.getPasswordSalt());
        if (checkpw) {
            redis.opsForValue().set("username",  JwtTokenUtil.generateToken(username));
            String salt = BCrypt.gensalt();
            String hashpw = BCrypt.hashpw(user.getPassword(),salt);
            user.setPasswordSalt(salt);
            user.setPassword(hashpw);
            if (service.updateById(user)){
                return ResponseResult.success();
            }else {
                return ResponseResult.fail();
            }
        }
        return ResponseResult.fail("密码不正确！");
    }
    //注册账号
    @RequestMapping("/register")
    public ResponseResult register(User user){
        String gensalt = BCrypt.gensalt();
        String hashpw = BCrypt.hashpw(user.getPassword(), gensalt);
        user.setPassword(hashpw);
        user.setCreateTime(new Date().getTime());
        user.setPasswordSalt(gensalt);
        if (service.updateById(user)){
            return ResponseResult.success();
        }else {
            return ResponseResult.fail();
        }
    }
    @PostMapping("/update")
    public ResponseResult update(@RequestBody User userInfo){
        return ResponseResult.success(service.updateById(userInfo));
    }
    // 新增
    @PostMapping("/insert")
    public ResponseResult insert(@RequestBody User userInfo){
        return ResponseResult.success(service.save(userInfo));
    }
}
