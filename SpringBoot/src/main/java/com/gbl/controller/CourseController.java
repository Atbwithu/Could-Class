package com.gbl.controller;

import com.gbl.entity.Course;
import com.gbl.entity.UserCourseStudy;
import com.gbl.service.ICategoryService;
import com.gbl.service.IChapterService;
import com.gbl.service.IStudyService;
import com.gbl.service.ICourseService;
import com.gbl.utils.UploadCoverUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/course")
public class CourseController {
    @Resource
    ICourseService service;
    @Resource
    ICategoryService categoryService;
    @Resource
    IStudyService serviceStudy;
    @Autowired
    IChapterService serviceChapter;

    @RequestMapping("/list")
    public List<Course> select(Course course){
        List<Course> list = service.select(course);
        return list;
    }

    @RequestMapping("/newCourses")
    public List<Course> newCourses(){
        List<Course> list = service.newCourses();
        return list;
    }
    //新增
    @CrossOrigin
    @RequestMapping("/insert")
    public int insert( @RequestParam("courseName") String courseName,
                       @RequestParam("courseTime") String courseTime,
                       @RequestParam("father") int father,
                       @RequestParam("child") int child,
                       @RequestParam("teacher") String teacher,
                       @RequestParam("order") int order,
                       @RequestParam("file") MultipartFile cover,
                       @RequestParam("rec") int rec
    ) throws IOException{
        // 调用UploadUtil类的uploadImage方法，并传入MultipartFile对象
        UploadCoverUrl coverUrl = new UploadCoverUrl();
        String string = coverUrl.saveCourseUrl(cover);
        Course course = new Course(courseName,courseTime,teacher,order,rec);
        course.setCover(string);
        int insert = service.insert(course);
        if (father<=0){
            return -2;
        }else if (child <=0){
            return -3;
        }

        Integer id = course.getId();
        int i = categoryService.forCourseKind(id, father, child);
        return i;
    }

    //上传图片
    @RequestMapping("/upload")
    public String upload(MultipartFile file) throws IOException {
        String uploadedImageUrl = UploadUtil.uploadImage(file);
        // 打印或返上传后的图片URL
        System.out.println("Uploaded Image URL: " + uploadedImageUrl);
        return  uploadedImageUrl;
    }

    @RequestMapping("/delete")
    public int delete(int id){
        //再删课程
        int i = service.delete(id);

        return i;
    }
    @PostMapping("/fakeDelete")
    public int fakeDelete(int id){
        return service.fakeDelete(id);
    }
    @PostMapping("/recover")
    public int recover(int id){
        return service.recover(id);
    }


    @PostMapping("/selectById")
    public Course selectById(int id){
        Course course =service.selectById(id);
        return course;
    }
    @GetMapping("/findCourseById")
    public Course findCourseById(int id){
        Course course =service.findCourseById(id);
        return course;
    }
    @CrossOrigin
    @PostMapping("/update")
    public int update(@RequestParam("id") int id,
                      @RequestParam("courseName") String courseName,
                      @RequestParam("courseTime") String courseTime,
                      @RequestParam("teacher") String teacher,
                      @RequestParam("order") int order,
                      @RequestParam("file") MultipartFile cover
    ) throws IOException{
        UploadCoverUrl coverUrl = new UploadCoverUrl();
        String string = coverUrl.saveCourseUrl(cover);
        Course course = new Course(id,courseName,courseTime, teacher,order,string);
        int update = service.update(course);
        return update;
    }
    //收藏课程
    @RequestMapping("collect")
    public int collect(int uid,int cid){
        UserCourseStudy list = serviceStudy.selectUser(uid, cid);
        if (list!=null){
            return 1;
        }else {
            //新增课程的学习记录表
            UserCourseStudy study = new UserCourseStudy();
            service.collect(study);
            Integer sid = study.getId();
            //获取的id新增课程的学习记录跟课程关联
            service.courseAndStudy(cid,sid);
            //然后新增用户跟学习记录进行关联
            service.userAndStudy(uid,sid);
            return 0;
        }

    }
    //不推荐
    @RequestMapping("updateRecById1")
    public int updateRecById1(@RequestParam int id){
        int i = service.updateRecByIdA(id);
        return i;
    }
    //推荐
    @RequestMapping("updateRecById2")
    public int updateRecById2(@RequestParam int id){
        int i = service.updateRecByIdB(id);
        return i;
    }
    //查询父类的类别课程的接口
    @RequestMapping("/fatherCourse")
    public List<Course> fatherCourse(int id){
        List<Course> list = service.fatherCourse(id);
        return list;
    }
    //查询子类的类别课程
    @RequestMapping("/childCourse")
    public List<Course> childCourse(int id){
        List<Course> list = service.childCourse(id);
        return list;
    }
    //最新课程
    @RequestMapping("/newCourse")
    public List<Course> newCourse(){
        List<Course> courses = service.newCourse();
        return courses;
    }
    //最热门课程
    @RequestMapping("/hotCourse")
    public List<Course> hotCourse(){
        List<Course> courses = service.hotCourse();
        return courses;
    }

}
