package com.gbl.service.kind;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gbl.entity.Course;
import com.gbl.entity.Kinds;
import com.gbl.mapper.CourseMapper;
import com.gbl.mapper.KindsMapper;
import com.gbl.service.course.ICourseService;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * @Author: Insight
 * @Description: TODO
 * @Date: 2024/5/1 15:42
 * @Version: 1.0
 */
@Service
public class KindServiceImpl extends ServiceImpl<KindsMapper, Kinds> implements IKindsService {

}
