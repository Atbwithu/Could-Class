package com.gbl.mapper;

import com.gbl.entity.SomeId;
import com.gbl.entity.Course;
import com.gbl.entity.UserCourseStudy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: 哥布林酋长
 * @Description: 课程-接口
 * @Date: 2024/5/1 15:23
 * @Version: 1.0
 */@Mapper
public interface CourseMapper {
    //    增 删 改 查
    int insert(Course course);
    int recover(int cid);
    int delete(int id);
    //根据课程id 查询关联表的id去进行删除
    List<SomeId> getChapterIds(int id);
    //删除章节和课程的关联表
    int deleteRelationship(int id);
    Course selectById(int id);
    Course findCourseById(int id);
    int update(Course course);


    /**
     * 新增课程
     * 先新增一行课程记录
     * @return
     */
    int collect(UserCourseStudy study);

    /**
     * 新增课程
     * 新增学习记录和课程关联的一行数据
     * @return
     */
     int courseAndStudy(int cid,int sid);
    /**
     * 新增课程
     * 将用户跟课程关联起来
     * @return
     */
    int userAndStudy(int uid,int sid);
    List<Course> newCourses();
    //查询父类的类别课程
    List<Course> fatherCourse(int id);
    //查询子类的类别课程
    List<Course> childCourse(int id);
    //最新课程课程
    List<Course> newCourse();
    //查询最热课程
    List<Course> hotCourse();
    List<Course> select(Course course);
    //查询课程关联的章节id进行删除
    List<SomeId> findChapterId(int id);


}
