package com.gbl.service.impl;


import com.gbl.entity.Chapter;
import com.gbl.entity.ChapterStudy;
import com.gbl.entity.SomeId;
//import com.gbl.entity.CourseMapper;
import com.gbl.entity.Video;
import com.gbl.mapper.CategoryMapper;
import com.gbl.mapper.ChapterMapper;
import com.gbl.mapper.StudyMapper;
import com.gbl.service.IChapterService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ChapterServiceImpl  implements IChapterService {

    @Autowired
    ChapterMapper mapper;

    @Autowired
    StudyMapper studyMapper;
    @Resource
    StringRedisTemplate redis;

    @Override
    public List<Chapter> findAll(int id) {
        return mapper.findAll(id);
    }

    @Override
    public List<Chapter> chapterName(int id) {
        List<Chapter> chapters = mapper.chapterName(id);
        return chapters;
    }

    @Override
    public List<Video> findAllVideo(int id) {
        redis.opsForValue().set("chapterId",String.valueOf(id));
        return mapper.findAllVideo(id);
    }

    @Override
    public int deleteById(List<SomeId> ids) {
        int i = mapper.deleteById(ids);
        return i;
    }

    @Override
    public List<SomeId> getVideoIdById(int id) {
        //查询视频id
        List<SomeId> videoId = mapper.getVideoIdById(id);
        //删除视频
        mapper.deleteVideoById(videoId);
        //删除视频文件
        return videoId;
    }

    @Override
    public List<Video> selectVideo(int id) {
        return mapper.selectVideo(id);
    }


    @Override
    public List<Video> findVideo(int id) {
        return mapper.findVideo(id);
    }

    @Override
    public Video onlyVideo(int vid, int cid) {
        return mapper.onlyVideo(vid,cid);
    }

    @Override
    public int uploadVideo(String videoName, String savePath,String originalName) {
        Video video = new Video();
        video.setVideoName(videoName);
        video.setSavePath(savePath);
        video.setOriginalName(originalName);
        mapper.uploadVideo(video);
        int vid = video.getId();
        String cid = redis.opsForValue().get("chapterId");
        mapper.videoAndChapter(vid,Integer.valueOf(cid));
        return 0;
    }

    @Override
    public int addChapter(String name, int courseId) {
        if (name !=null){
            Chapter chapter = new Chapter();
            //设置时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date date = new Date();
            String format = sdf.format(date);
            chapter.setCreateTime(format);
            chapter.setName(name);

            //新增章节
            mapper.addChapter(chapter);
            int chapterId = chapter.getId();
            //新增章节与课程的关联中间表
            mapper.chapterCourse(chapterId,courseId);
            ChapterStudy chapterStudy = new ChapterStudy(0,0);

            //新增章节学习记录
            studyMapper.addChapterStudy(chapterStudy);
            int sid = chapterStudy.getId();
            //新增章节的学习记录中间表
            studyMapper.chapterStudy(chapterId,sid);
            return 0;
        }
        return -1;
    }

    @Override //删除单个章节
    public int deleteChapterById(int id) {
        //删除单个章节
        mapper.deleteChapterById(id);
        //删除单个的关联
        mapper.removeChapterVideo(id);
        return 0;
    }

    @Override //删除多个个章节
    public int deleteChapterByIds(int[] ids) {
        mapper.deleteChapterByIds(ids);
        return 0;
    }

    @Override
    public int deleteVideo(int id) {
        return 0;
    }

//    @Override //删除视频
//    public int deleteVideo(int id) {
//        mapper.deleteVideo(id);
//        //删除视频关联
//        mapper.delVideoRelationship(id);
//        //删除视频文件
//        Video video = mapper.selectById(id);
//        String savePath = video.getSavePath();
//        File file = new File(savePath);
//        if (file.exists() && file.isFile()){
//            file.delete();
//            return 0;
//        }else {
//            return -1;
//        }
//    }

    @Override //批量删除视频
    public int deleteVideos(int[] ids) {
        //删除视频
        mapper.deleteVideos(ids);
        //删除视频关联
        mapper.delRelationshipByIds(ids);
        return 0;
    }


}
