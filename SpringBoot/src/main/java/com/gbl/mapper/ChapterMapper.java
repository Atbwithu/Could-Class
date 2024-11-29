package com.gbl.mapper;


import com.gbl.entity.Chapter;
import com.gbl.entity.ChapterStudy;
import com.gbl.entity.SomeId;
//import com.gbl.entity.SubjectChapter;
import com.gbl.entity.Video;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
public interface ChapterMapper {
    /**
     * 新增
     */
    //新增章节
    int addChapter(Chapter chapter);
    //新增章节关联课程
    int chapterCourse(int chapterId, int courseId);
    //上传视频
    int uploadVideo(Video video);
    //上传视频时新增视频跟课程的关联
    @Transactional
    //新增视频关联的课程
    int videoAndChapter(int vid,int cid);
    /**
     *查询
     */
    //查询所以章节
    List<Chapter> findAll(int id);
    //查询所有章节的名称
    List<Chapter> chapterName(int id);
    //查询章节所有的视频id
    List<SomeId> getVideoIdById(int id);
    List<Video> findVideo(int id);
    //查询具体章节下的一条视频
    Video onlyVideo(int vid, int cid);
    //查询章节下所有的视频
    List<Video> findAllVideo(int id);
    //查询批量查询章节关联的视频id
    List<SomeId> findVideoId(int id);
    //查询视频详情
    List<Video> selectVideo(int id);

    /**
     * 删除
     */
    //删除单个章节
    @Transactional
    int deleteChapterById(int id);
    //删除多个个章节
    @Transactional
    int deleteChapterByIds(int[] ids);
    //删除章节关联的视频列表
    @Transactional
    int removeChapterVideo(int id);
    //删除单个视频
    @Transactional
    int deleteVideo(int id);
    //删除单个视频的关联
    int delVideoRelationship(int id);
    //批量删除视频
    @Transactional
    int deleteVideos(int[] ids);
    //
    @Transactional
    int deleteById(List<SomeId> ids);
    //删除章节下所有的视频
    @Transactional
    int deleteVideoById(List<SomeId> ids);
    //删除视频关联
    @Transactional
    int delRelationshipByIds(int[] ids);
    //删除章节视频关联表

    /**
     * 修改
     */




}
