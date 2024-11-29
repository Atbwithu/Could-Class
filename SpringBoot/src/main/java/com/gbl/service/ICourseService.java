package com.gbl.service;

import com.gbl.entity.SomeId;
import com.gbl.entity.Course;
import com.gbl.entity.UserCourseStudy;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: 哥布林酋长
 * @Description: 课程-接口
 * @Date: 2024/5/1 15:23
 * @Version: 1.0
 */
public interface ICourseService {
    //    增 删 改 查
    @Transactional
    int insert(Course course);
    @Transactional
    int delete(int id);
    //伪删除课程
    @Transactional
    int fakeDelete(int id);
    @Transactional
    int recover(int id);
    //根据课程id 查询关联表的id去进行删除
    List<SomeId> getChapterIds(int id);

    Course selectById(int id);
    Course findCourseById(int id);
    @Transactional
    int update(Course course);
    List<Course> select(Course course);

    /**
     * 新增课程
     * 先新增一行课程记录
     * @return
     */
    @Transactional
    int collect(UserCourseStudy study);

    /**
     * 新增课程
     * 新增学习记录和课程关联的一行数据
     * @return
     */
    @Transactional
    int courseAndStudy(int cId,int sId);
    /**
     * 新增课程
     * 将用户跟课程关联起来
     * @return
     */
    int userAndStudy(int uId,int sId);
    //修改推荐
    int updateRecByIdA(int id);
    int updateRecByIdB(int id);
    //查询父类的类别课`

    List<Course> newCourses();
    List<Course> fatherCourse(int id);
    //查询子类的类别课程
    List<Course> childCourse(int id);
    //最新课程
    List<Course> newCourse();
    //查询最热课程
    List<Course> hotCourse();

}
