package com.gbl.service.impl;

import com.gbl.entity.SomeId;
import com.gbl.entity.Course;
import com.gbl.entity.UserCourseStudy;
import com.gbl.mapper.ChapterMapper;
import com.gbl.mapper.CourseMapper;
import com.gbl.service.ICourseService;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @Author: Insight
 * @Description: TODO
 * @Date: 2024/5/1 15:45
 * @Version: 1.0
 */
@Service
public class CourseServiceImpl implements ICourseService {
    @Resource
    CourseMapper mapper;
    @Resource
    ChapterMapper chapterMapper;


    @Override
    public int insert(Course course) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = new Date();
        String format = sdf.format(date);
        course.setCreateTime(format);
        return mapper.insert(course);
    }

    @Override
    public int delete(int id) {
        List<SomeId> chapterId = mapper.getChapterIds(id);
        if (chapterId.isEmpty()){
            mapper.delete(id);
            return 0;
        }else {
            mapper.delete(id);
            mapper.deleteRelationship(id);
            chapterMapper.deleteById(chapterId);
            return 0;
        }
    }

    @Override
    public int fakeDelete(int id) {
        Course course = mapper.findCourseById(id);
        course.setIsDelete(1);
        return mapper.update(course);
    }
    @Override
    public int recover(int id) {

        return mapper.recover(id);
    }

    @Override
    public List<SomeId> getChapterIds(int id) {
        List<SomeId> chapterIds = mapper.getChapterIds(id);
        chapterMapper.deleteById(chapterIds);
        return chapterIds;
    }

    @Override
    public Course selectById(int id) {
        return mapper.selectById(id);
    }

    @Override
    public Course findCourseById(int id) {
        return mapper.findCourseById(id);
    }

    @Override
    public int update(Course course) {
        return mapper.update(course);
    }

    @Override
    public List<Course> select(Course course) {
        return mapper.select(course);
    }

    @Override
    public int collect(UserCourseStudy study) {
        return mapper.collect(study);
    }

    @Override
    public int courseAndStudy(int cid, int sid) {
        return mapper.courseAndStudy(cid,sid);
    }

    @Override
    public int userAndStudy(int uid, int sid) {
        return mapper.userAndStudy(uid,sid);
    }

    @Override
    public int updateRecByIdA(int id) {
        Course course = mapper.findCourseById(id);
        course.setRec(5);
        return mapper.update(course);
    }
    @Override
    public int updateRecByIdB(int id) {
        Course course = mapper.findCourseById(id);
        course.setRec(1);
        return mapper.update(course);
    }

    @Override
    public List<Course> newCourses() {
        return mapper.newCourses();
    }
    //推荐视频
//    @Override
//    public int updateRecById1(int id) {
//        return mapper.updateRecById1(id);
//    }
//
//    @Override
//    public int updateRecById2(int id) {
//        return mapper.updateRecById2(id);
//    }

    @Override
    public List<Course> fatherCourse(int id) {
        return mapper.fatherCourse(id);
    }

    @Override
    public List<Course> childCourse(int id) {
        return mapper.childCourse(id);
    }

    @Override
    public List<Course> newCourse() {
        return mapper.newCourse();
    }

    @Override
    public List<Course> hotCourse() {
        return mapper.hotCourse();
    }



}
