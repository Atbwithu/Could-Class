package com.gbl.service.impl;

import com.gbl.entity.*;
import com.gbl.mapper.AskMapper;
import com.gbl.mapper.StudyMapper;
import com.gbl.service.IStudyService;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * @Author: Insight
 * @Description: TODO
 * @Date: 2024/5/1 15:49
 * @Version: 1.0
 */
@Service
public class StudyServiceImpl implements IStudyService {
    @Resource
    StudyMapper mapper;
    @Resource
    AskMapper askMapper;
    @Override
    public int insert(Study study) {
        return mapper.insert(study);
    }

    @Override
    public int delete(int id) {
        return mapper.delete(id);
    }

    @Override
    public Study selectById(int id) {
        return mapper.selectById(id);
    }

    @Override
    public int update(Study study) {
        mapper.update(study);
        return 0;
    }
//  用户课程学习记录的修改
    @Override
    public int courseUpdate(UserCourseStudy studyCourse) {
        return mapper.courseUpdate(studyCourse);
    }

    @Override
    public List<Study> select(Study study) {
        return mapper.select(study);
    }

    @Override
    public int handleUpdate(Study study) {
        return mapper.handleUpdate(study);
    }

    @Override
    public List<UserStudyCourse> selectUserCourse(UserStudyCourse userStudyCourse, int id) {
        return mapper.selectUserCourse(userStudyCourse,id);
    }

    @Override
    public UserCourseStudy selectUser(int userId, int courseId) {
        return mapper.selectUser(userId,courseId);
    }
//最新课程
    @Override
    public List<Course> newCourse() {
        return mapper.newCourse();
    }

    //统计最爱学习的学生
    public List<UserStudyCourse> loveStu() {
        return mapper.loveStu();
    }

    //    新增用户新增所需的表
    @Override
    public int nweStudy(int uid,int sid) {
        return mapper.nweStudy(uid,sid);
    }

    //统计提问次数
    public int countQuestionNum(int id) {

        return mapper.countQuestionNum(id);
    }

    @Override
    public int courseStudyById(int uid, int cid) {
        return mapper.courseStudyById(uid,cid);
    }
    //更章节学习次数
    @Override
    public int updateChapterLearn(int id) {
        ChapterStudy study = mapper.findChapterLearnById(id);
        study.setLearnNum(study.getLearnNum() + 1);
        askMapper.updateChapterStudy(study);
        return 0;
    }

    @Override
    public UserCourseStudy findCourseStudy(int uid, int cid) {
        return mapper.findCourseStudy(uid,cid);
    }
}
