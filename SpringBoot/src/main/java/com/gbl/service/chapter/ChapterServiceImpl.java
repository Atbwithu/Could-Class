package com.gbl.service.chapter;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gbl.entity.Chapter;
import com.gbl.entity.ChapterStudy;
//import com.gbl.entity.CourseMapper;
import com.gbl.entity.Video;
import com.gbl.mapper.ChapterMapper;
import com.gbl.mapper.StudyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements IChapterService {



}
