package com.gbl.service;

import com.gbl.entity.*;
import com.gbl.utils.ToKenUtil;
import com.github.pagehelper.PageInfo;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: 哥布林酋长
 * @Description: 用户接口
 * @Date: 2024/5/1 15:19
 * @Version: 1.0
 */
public interface IUserService {
//    增 删 改 查
//    增 删 改 查
    int insert(User user);
    int insert2(User user);
    int delete(int id);
    User selectById(int id);
    int update(User user);
    List<User> findBySearch(User user);

    int countStu();
    int countQuestion();
    int countCourse();
    User login(@Param("account") String account,@Param("password") String password);
    //    注册用户
    int register(User user);
    //    获取用户关联学习表的id
    int getId(int id);
    //    删除关联表
    int delStudy(int id);
    User selByName(String account);
    User selByName2(String account);

//    UserInfo  loginByPhone(@Param("phone") String phone,@Param("password") String password);
    //修改账号状态
    int updateStatus(@Param("id") Integer id,@Param("status") Integer status);
    @Transactional
    int insertUserRole(int id, int roleId);
    List<User> nickName(String userName);

}
