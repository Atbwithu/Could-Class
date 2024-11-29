package com.gbl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gbl.entity.Course;
import com.gbl.entity.UserCourseStudy;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: 哥布林酋长
 * @Description: 课程-接口
 * @Date: 2024/5/1 15:23
 * @Version: 1.0
 */@Mapper
public interface CourseMapper extends BaseMapper<Course> {
}
