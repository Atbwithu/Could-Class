package com.gbl.controller.study;

import com.gbl.entity.*;
import com.gbl.service.study.IStudyService;
import com.gbl.service.course.ICourseService;
import com.gbl.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/study")
public class StudyController {
    @Resource
    IStudyService service;
    @Resource
    IUserService service2;
    @Resource
    ICourseService serviceCourse;

    @Autowired
    StringRedisTemplate redisTemplate;
    @RequestMapping("/list")
    public List<Study> select(Study study) {
        List<Study> list = service.select(study);
        return list;
    }

    @RequestMapping("/insert")
    public int insert(Study study) {
        int i = service.insert(study);
        return i;
    }

    @RequestMapping("/delete")
    public int delete(int id) {
        int i = service.delete(id);
        return i;
    }

    @RequestMapping("/selectById")
    public Study selectById(int id) {
        Study study = service.selectById(id);
        return study;
    }

    @RequestMapping("/update")
    public int update(Study study) {
        int i = service.update(study);
        return i;
    }

    //    学习时长记录
    @RequestMapping("/handleTimeUpdate")
    public int handleTimeUpdate(int userId, String videoTime,int courseId,int chapterId) {
        /**
         * 判断是否有该课程如果没有则只更新总学习记录，有则都更新
         */
        //        获取用户
        Study std = service.selectById(userId);
        // 获取用户的章节学习记录
        UserCourseStudy stdCourse = service.selectUser(userId,courseId);
        //

        //将获取到的时间进行格式化
        Double loadTime = Double.valueOf(videoTime);
        loadTime=loadTime/3600;

        //更新章节的学习记录
        service.updateChapterLearn(chapterId);
        //判断用户是否收藏课程，如果收藏全都更新，反则就更新总学习记录
        if (stdCourse!=null){
            Double courseTime = stdCourse.getLearnTime();
            //时间格式化
            courseTime = courseTime + loadTime;
            //总的学习记录
            //更新状态
            stdCourse.setState(1);
            //将学习时间更新上
            //更新总时间
            stdCourse.setLearnTime(courseTime);
            service.courseUpdate(stdCourse);
            //将学习时间记录到总时间
            Double newHour = std.getLearnTime();
            newHour = newHour+courseTime;
            std.setLearnTime(newHour);
            service.handleUpdate(std);
            return 0;
        }else {
            /**
             * 只更新总学习记录
             */
            //将学习时间记录到总时间
            Double newHour = std.getLearnTime();
            newHour = newHour+loadTime;
            std.setLearnTime(newHour);
            service.handleUpdate(std);
            return 1;
        }


    }
//用户全部的学习记录
    //    修改学习时长，次数
    @RequestMapping("/handleNumUpdate")
    public int handleNumUpdate(int userId,int courseId) {
//        获取查询谁在看视频
        Study std = service.selectById(userId);
        int num = std.getLearnNum();
        num+=1;
        std.setLearnNum(num);
        int i = service.handleUpdate(std);
        return i;
    }
    //    修改提问
    @RequestMapping("/handleQuestionUpdate")
    public int handleQuestionUpdate(int userId,int courseId) {
//        获取用户
        Study std = service.selectById(userId);
//        获取用户的章节学习记录
        UserCourseStudy studyCourse = service.selectUser(userId,courseId);
//        获取对象提问数据
        int count = studyCourse.getAskNum();
        count +=1;
//        修改数据
        studyCourse.setAskNum(count);
//         将课程提问的记录修改
        service.courseUpdate(studyCourse);
        int num = std.getQuestionNum();
        num+=count;
        std.setQuestionNum(num);
            int i = service.handleUpdate(std);
        return i;
    }
//    用户具体的课程学习记录
    @RequestMapping("/userStudy")
    public List<UserStudyCourse> userCourser(UserStudyCourse userStudyCourse,int id){
            List<UserStudyCourse> list = service.selectUserCourse(userStudyCourse,id);

        return list;
    }
//    课程提问更新
    @RequestMapping("/updateUserCourse")
    public int updateUserCourse(int id) {
        try {
            int questionNum = service.countQuestionNum(id);
            Study study = service.selectById(id);
            Integer num = study.getCollectNum();
            questionNum +=num;
            study.setQuestionNum(questionNum);
            service.handleUpdate(study);
            return 0;
        } catch (Exception e) {
            // 记录异常信息
            return -1;
        }
    }
    //    最爱学习学生的列表
    @RequestMapping("/loveStu")
    public List<UserStudyCourse> loveStu(){
        return service.loveStu();
    }
//    最新课程

    @RequestMapping("/newCourse")
    public List<Course> newCourse(){
        return service.newCourse();
    }
    //记录课程所提问的次数
    @RequestMapping("/courseStudyById")
    public int courseStudyById(int uid,int cid){
        //用户在该查询出课程的数量
        int num = service.courseStudyById(uid, cid);
        //
        UserCourseStudy userCourseStudy = service.selectUser(uid, cid);
        userCourseStudy.setAskNum(num);
        userCourseStudy.setCid(cid);
        service.courseUpdate(userCourseStudy);
        return 0;
    }
}
