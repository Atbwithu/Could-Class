package com.gbl.mapper;

import com.gbl.entity.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: 哥布林酋长
 * @Description: 问答表-接口
 * @Date: 2024/5/1 15:23
 * @Version: 1.0
 */@Mapper
public interface StudyMapper {
    //    增 删 改 查
    //新增章节时同时新增学习及记录跟章节的关系
    int chapterStudy(int chapterId,int sid);
    int addChapterStudy(ChapterStudy chapterStudy);
    int insert(Study study);
    //    新增用户新增所需的表
    int nweStudy(int uid,int sid);
    int delete(int id);
    Study selectById(int id);
//    修改学习记录
    int handleUpdate(Study study);
    int update(Study study);
    int courseUpdate(UserCourseStudy studyCourse);
    List<Study> select(Study study);
//    学习时长
//章节时长记录
    List<UserStudyCourse> selectUserCourse(UserStudyCourse userStudyCourse,int id);
//    查询用户单个章节记录
    UserCourseStudy selectUser(int userId , int courseId);
//    记录最爱学习的学生
    List<UserStudyCourse> loveStu();
    //    最热课程
    List<Course> newCourse();

    int countQuestionNum(int id);
    //用户在课程所提问的课程数
    //查询学习次数
    ChapterStudy findChapterLearnById(int id);
    int courseStudyById(int uid,int cid);
    //    <!--    查询出学习记录表-->
    UserCourseStudy findCourseStudy(int uid,int cid);

}
