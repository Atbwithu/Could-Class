package com.gbl.controller;

import com.gbl.entity.Chapter;
import com.gbl.entity.Video;
import com.gbl.service.IChapterService;
import com.gbl.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

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
        List<Video> video = service.findVideo(id);
        return video;
    }
    // 查询视频
    @RequestMapping("/selectVideo")
    public List<Video> selectVideo(int id) {
        List<Video> videos = service.selectVideo(id);
        return videos;
    }

    // 查询具体章节下的一条视频的接口
    @RequestMapping("/onlyVideo")
    public Video onlyVideo(int vid, int cid) {
        Video video = service.onlyVideo(vid, cid);
        return video;
    }
    //上传视频接口
    @PostMapping(value = "/uploadVideo")
    @ResponseBody
    public int uploadVideo(@RequestParam("file") MultipartFile file,  @RequestParam("videoName") String videoName) {
        String savePath = null;
        try {
            savePath = UploadUtil.uploadVideo(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 检查文件是否为空
        if (file.isEmpty()) {
            return 1;
        }
        //获取文件后缀，因此此后端代码可接收一切文件，上传格式前端限定
//            String fileExt = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();
        // 获取文件原始名称
//            String originalName = file.getOriginalFilename();
        // 定义保存文件的路径
//            String pikId = UUID.randomUUID().toString().replaceAll("-", "");
        // 视频名字拼接：唯一标识符加上点，再加上上面的视频后缀也就是 MP4 之类的。就组成了现在的视频名字，比如这样：c7bbc1f9664947a287d35dd7cdc48a95.mp4
//            String newVideoName = pikId + "." + fileExt;
//            System.out.println("重构文件名防止上传同名文件：" + newVideoName);
        // 保存视频的原始名字
        String originalName = file.getOriginalFilename();
        System.out.println("视频原名:" + originalName);

        // 新的路径处理方式
//            Path currentWorkingDirectory = FileSystems.getDefault().getPath("").toAbsolutePath();
//            Path combinedPath = currentWorkingDirectory.resolve(savePath + "/" + newVideoName);
//            String videoUrl = combinedPath.toString();

        // 判断路径对应的文件夹是否存在
//            File filepath = new File(combinedPath.toString());
//            if (!filepath.getParentFile().exists()) {
//                // 如果不存在，就创建一个这个路径的文件夹。
//                filepath.getParentFile().mkdirs();
//            }

        // 保存视频
//            File fileSave = new File(combinedPath.toString());
        // 下载视频到文件夹中
//            file.transferTo(fileSave);
        service.uploadVideo(videoName, savePath,originalName);
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