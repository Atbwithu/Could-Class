package com.gbl.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.gbl.entity.*;
import com.gbl.service.IStudyService;
import com.gbl.service.IUserService;
import com.gbl.service.OutLog;
import com.gbl.utils.*;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    IUserService service;
//    IAskService service1;
    @Resource
    OutLog outLog;
    @Resource
    IStudyService serviceStudy;
    @RequestMapping("/list")
    public R<List> select(User user){
        List<User> bySearch = service.findBySearch(user);
        return R.success(bySearch);
    }
    @RequestMapping("/countStu")
    public int countStu(){

        int i = service.countStu();
        int o = i;
        return o;
    }
    @RequestMapping("/countQuestion")
    public int countQuestion(){
        int i = service.countQuestion();
        int o = i;
        return o;
    }
    @RequestMapping("/countCourse")
    public int countCourse(){
        int i = service.countCourse();
        int o = i;
        return o;
    }
    @RequestMapping("/delete")
    public int delete(int id){
//        删除用户
         service.delete(id);
//        删除用户所关联信息
        int fid = service.getId(id);
//        根据查到的id删除学习记录表
        serviceStudy.delete(fid);
//        最后删除关联表
        service.delStudy(id);
        return 0;
    }
    @RequestMapping("/selectById")
    public User selectById(int id){
        User user =service.selectById(id);
        return user;
    }

    @PostMapping("/login")
    public R<User> login(String account, String password){
        User userInfo = service.login(account, password);
        if (StringUtils.isNoneBlank(account) && StringUtils.isNoneBlank(password) && userInfo != null){
            if (userInfo.getAccount().equals(account) && userInfo.getPassword().equals(password)){
                JSONObject jsonObject = JSONUtil.createObj().put("name", account);
                String token = ToKenUtil.createToken(jsonObject);
                userInfo.setToken(token);
                userInfo.setPassword(null);
                return R.success(userInfo);
            }
        }
        return R.error(ResponseNum.account_PASSWORD_ERROR);
    }

    @Auth
    @RequestMapping("/outLog")
    public R<Result> outLog(String token){
        boolean tokenDestroyed = ToKenUtil.isTokenDestroyed(token);
        System.out.println(tokenDestroyed);
        return R.success(Result.success(outLog.outLog(token)));
    }
//    注册账号
    @RequestMapping("/register")
    public int register(User user){
//        注册账号
        service.register(user);
//        获取用户的id
        int cid = user.getId();
//        新增该学生的学习表
        Study study = new Study();
        serviceStudy.insert(study);
        Integer sid = study.getId();
//      获取两个id新增到关联表
        serviceStudy.nweStudy(cid,sid);
        return 0;
    }
    @Auth
    @PostMapping("/update")
    public R<User> update(@RequestBody User userInfo){
        int i = service.update(userInfo);
        if (i == 1){
            User info = service.selectById(userInfo.getId());
            return R.success(info);
        }
        if (i == 0){
            return R.error(ResponseNum.FAIL);
        }
        return R.success(userInfo);
    }
    // 新增
    @Auth
    @PostMapping("/insert")
    public R<Integer> insert(@RequestBody User userInfo){
        int result = service.insert(userInfo);

        service.insertUserRole(userInfo.getId(),userInfo.getRoleId());
        int uid = userInfo.getId();
        Study study = new Study();
        serviceStudy.insert(study);
        Integer sid = study.getId();
        serviceStudy.nweStudy(uid,sid);
        if (result == 0) {
            return R.error(400,"新增失败");
        }
        return R.success(200);
    }
    @PostMapping("/insert2")
    public R<Integer> insert2(@RequestBody User userInfo){
        String imgUrl = "https://q.qlogo.cn/headimg_dl?dst_uin="+ userInfo.getEmail()+"&spec=5";
        String email = userInfo.getEmail()+"@qq.com";
        userInfo.setEmail(email);
        userInfo.setImageUrl(imgUrl);
        userInfo.setRoleId(3);
        userInfo.setPassword("123");
        LocalDateTime now = LocalDateTime.now();
        userInfo.setCreateTime(String.valueOf(now));
        int result = service.insert2(userInfo);
        service.insertUserRole(userInfo.getId(),userInfo.getRoleId());
        int uid = userInfo.getId();
        Study study = new Study();
        serviceStudy.insert(study);
        Integer sid = study.getId();
        serviceStudy.nweStudy(uid,sid);
        if (result == 0) {
            return R.error(400,"新增失败");
        }
        return R.success(200);
    }
    //查询名字
    @Auth
    @PostMapping("/selectByName")
    public R<String> selectByName(@RequestBody String account){
        User name = service.selByName(account);
        if (name == null) {
            return R.success("用户名可用");
        }
        return R.error(400,"用户已存在");
    }

    @PostMapping("/selectByName2")
    public R<String> selectByName2(@RequestBody String account){
        String account1 = account;
        String newaccount = account1.substring(0, account1.length() - 1);
        User name = service.selByName2(newaccount);
        if (name == null) {
            return R.success("用户名可用");
        }
        return R.error(400,"用户已存在");
    }
    //查询昵称
    @Auth
    @RequestMapping("/selectByNickName")
    public R<List> selectByNickName(String userName){
        List<User> userInfo = service.nickName(userName);
        if (userInfo == null) {
            return R.error(400,"用户已存在");
        }
        return R.success(userInfo);
    }
//    根据ID查询
//    @Auth
//    @RequestMapping("/selectById")
//    public R<User> selectById(int id){
//        User userInfo = service.selectById(id);
//        if (userInfo == null) {
//            return R.error(400, "用户不存在");
//        }
//        return R.success(userInfo);
//    }
    // 删除
    @Auth
    @RequestMapping("/deleteUser")
    public R<Integer> deleteUser(int id){
        int result = service.delete(id);
        if (result == 0){
            R.error(400,"删除失败");
        }
        return R.success(result);
    }
    //修改状态
    @Auth
    @PostMapping("/updateStatus")
    public R<Integer> updateStatus(int id,int status){
        if (status == 1){
            status =2;
        }else {
            status = 1;
        }
        int result = service.updateStatus(id,status);
        return R.success(result);
    }
    //修改用户信息
    @Auth
    @RequestMapping("/updateById")
    public R<Integer> updateById(@RequestBody User userInfo){
        int result = service.update(userInfo);
        if (result == 1){
            return R.success(result);
        }
        return R.error(400,"修改失败");
    }




}
