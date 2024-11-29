package com.gbl.service;


import com.gbl.entity.Chapter;
import com.gbl.entity.SomeId;
import com.gbl.entity.Video;
import org.springframework.transaction.annotation.Transactional;
//import com.gbl.entity.SubjectChapter;

import java.util.List;

public interface IChapterService  {
    List<Chapter> findAll(int id);
    //查询所有章节的名称
    List<Chapter> chapterName(int id);
    //查询章节下所有的视频
    List<Video> findAllVideo(int id);
    @Transactional
    int deleteById(List<SomeId> ids);
    List<SomeId> getVideoIdById(int id);
    List<Video> selectVideo(int id);

    /**
     * 视频接口
     */
    List<Video> findVideo(int id);
    //查询具体章节下的一条视频
    Video onlyVideo(int vid, int cid);
    //更新视频
    @Transactional
    int uploadVideo(String videoName,String filePath,String originalName);


    /**
     *新增
     */
    @Transactional
    //新增章节
     int addChapter(String name, int courseId);
    /**
     * 删除
     */
    //删除单个章节
    int deleteChapterById(int id);
    //删除多个个章节
    int deleteChapterByIds(int[] ids);
    //删除视频
    int deleteVideo(int id);
    //批量删除视频
    int deleteVideos(int[] ids);

}
