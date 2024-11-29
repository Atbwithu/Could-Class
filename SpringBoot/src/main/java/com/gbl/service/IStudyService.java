package com.gbl.service;

import com.gbl.entity.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: 哥布林酋长
 * @Description: 学习记录
 * @Date: 2024/5/1 15:49
 * @Version: 1.0
 */
public interface IStudyService {
    //    增 删 改 查
    @Transactional
    int insert(Study study);
    //    新增用户新增所需的表
    @Transactional
    int nweStudy(int uid,int sid);
    @Transactional
    int delete(int id);

    Study selectById(int id);
    @Transactional
    int update(Study study);
    @Transactional
    int courseUpdate(UserCourseStudy studyCourse);
    List<Study> select(Study study);
    List<UserStudyCourse> loveStu();
    @Transactional
    int handleUpdate(Study study);
    List<UserStudyCourse> selectUserCourse(UserStudyCourse userStudyCourse,int id);
    UserCourseStudy selectUser(int userId, int courseId);
//    最新课程
    List<Course> newCourse();
    int countQuestionNum(int id);
    //用户在课程所提问的课程数
    int courseStudyById(int uid,int cid);

    //更新章节学习次数
    int updateChapterLearn(int id);

    //    <!--    查询出学习记录表-->
    UserCourseStudy findCourseStudy(int uid,int cid);

}
