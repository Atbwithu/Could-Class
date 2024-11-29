package com.gbl.service.course;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gbl.entity.Course;
import com.gbl.entity.UserCourseStudy;
import com.gbl.mapper.ChapterMapper;
import com.gbl.mapper.CourseMapper;
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
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

}
