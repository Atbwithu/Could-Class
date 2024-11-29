package com.gbl.service.impl;

import com.gbl.entity.*;
import com.gbl.mapper.UserMapper;
import com.gbl.service.IUserService;
import com.gbl.utils.ToKenUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author: Insight
 * @Description: TODO
 * @Date: 2024/5/1 15:46
 * @Version: 1.0
 */
@Service
public class UserServiceImpl implements IUserService {
    @Resource
    UserMapper mapper;
    @Resource
    ToKenUtil toKenUtil;
    @Override
    public int insert(User user) {
        return mapper.insert(user);
    }

    @Override
    public int insert2(User user) {
        return mapper.insert2(user);
    }

    @Override
    public int delete(int id) {
        return mapper.delete(id);
    }

    @Override
    public User selectById(int id) {
        return mapper.selectById(id);
    }

    @Override
    public int update(User user) {
        return mapper.update(user);
    }

    @Override
    public List<User> findBySearch(User user) {

        List<User> select = mapper.select(user);
        return select;
    }


    @Override
    public int countStu() {
        return mapper.countStu();
    }

    @Override
    public int countQuestion() {
        return mapper.countQuestion();
    }

    @Override
    public int countCourse() {
        return mapper.countCourse();
    }

    @Override
    public User login(String account, String password) {
        return mapper.login(account,password);
    }

    @Override
    @Transactional
    public int register(User user) {
        SimpleDateFormat sdf  =  new  SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = new Date();
        String time = sdf.format(date);
        user.setCreateTime(time);
        return mapper.register(user);
    }
    //    获取用户关联学习表的id
    @Override
    public int getId(int id) {
        return mapper.getId(id);
    }

    @Override
    public int delStudy(int id) {
        return mapper.delStudy(id);
    }

    @Override
    public User selByName(String account) {
        return mapper.selByName(account);
    }

    @Override
    public User selByName2(String account) {
        return mapper.selByName2(account);
    }

    @Override
    public int updateStatus(Integer id, Integer status) {
        int i = mapper.updateStatus(id, status);
        return i;
    }

    @Override
    public int insertUserRole(int id, int roleId) {
        return mapper.insertUserRole(id,roleId);
    }

    @Override
    public List<User> nickName(String userName) {
        return mapper.nickName(userName);
    }


}
