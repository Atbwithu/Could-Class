package com.gbl.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gbl.entity.Chapter;
//import com.gbl.entity.SubjectChapter;
import com.gbl.entity.Video;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
public interface ChapterMapper extends BaseMapper<Chapter> {
}
