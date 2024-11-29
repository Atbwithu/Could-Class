package com.gbl.controller.chapter;

import com.gbl.entity.Chapter;
import com.gbl.entity.Video;
import com.gbl.service.chapter.IChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/chapter")
public class ChapterController {

    @Autowired
    IChapterService service;



    // 查询所有章节接口
    @RequestMapping("/findAll")
    public List<Chapter> findAll(int id) {
        return service.findAll(id);
    }

    // 查询所有章节接口
    @RequestMapping("/chapterName")
    public List<Chapter> chapterName(int id) {
        return service.chapterName(id);
    }

    // 查询视频
    @RequestMapping("/findVideo")
    public List<Video> findVideo(int id) {
        return service.findVideo(id);
    }
    // 查询视频
    @RequestMapping("/selectVideo")
    public List<Video> selectVideo(int id) {
        return service.selectVideo(id);
    }

    // 查询具体章节下的一条视频的接口
    @RequestMapping("/onlyVideo")
    public Video onlyVideo(int vid, int cid) {
        return service.onlyVideo(vid, cid);
    }
    //上传视频接口
    @PostMapping(value = "/uploadVideo")
    @ResponseBody
    public int uploadVideo(@RequestParam("file") MultipartFile file,  @RequestParam("videoName") String videoName) {
        return 0;
    }

    // 新增章节
    @RequestMapping("/addChapter")
    public int addChapter(String name, int courseId) {
        return service.addChapter(name, courseId);
    }

    // 查询章节下所有的视频
    @GetMapping("/findAllVideo")
    public List<Video> findAllVideo(int id) {
        return service.findAllVideo(id);
    }
    //删除章节接口
    @RequestMapping("/deleteChapterById")
    public int deleteChapterById(int id){
        return service.deleteChapterById(id);
    }
    //删除多个章节接口
    @RequestMapping("/deleteChapterByIds")
    public int deleteChapterByIds(int[] ids){
        return service.deleteChapterByIds(ids);
    }
    //删除多个视频接口
    @RequestMapping("/deleteVideos")
    public int deleteVideos(int[] ids) {
        return service.deleteVideos(ids);
    }
    //删除多个视频接口
    @RequestMapping("/deleteVideo")
    public int deleteVideo(int id){
        return service.deleteVideo(id);
    }


}