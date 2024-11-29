package com.gbl.controller;


import com.gbl.entity.*;
import com.gbl.service.IAskService;
import com.gbl.service.IStudyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/ask")
public class AskController {
    @Resource
    IAskService service;
    @Resource
    IStudyService serviceStudy;
    @Resource
    StringRedisTemplate redis;
    @GetMapping("/list")
    public List<Ask> select(Ask ask) {
        return service.select(ask);
    }
//    @RequestMapping("/testPage")
//    public PageInfo<Ask> testPage(int i,int o){
//        IPage<Ask> select = service.queryPage(i,o);
//        List<Ask> list = select.getList();
//
//        return select;
//    }
    @RequestMapping("/insert")
    public int insert(Ask ask){
        int i = service.insert(ask);
        return i;
    }
    @RequestMapping("/delete")
    public int delete(int id){
        int i = service.delete(id);
        return i;
    }
    @RequestMapping("/selectById")
    public Ask selectById(int id){
        Ask ask =service.selectById(id);

        return ask;
    }
    @RequestMapping("/update")
    public int update(Ask ask){
        int i =service.update(ask);
        return i;
    }
//    提问
    @RequestMapping("/subAsk")
    public int subAsk(String question,int cid,int uid,int chapterId) {

        Ask ask = new Ask();

        ask.setQuestions(question);
        // 新增提问
            service.ask(ask);
            // 获取 id
            int qid = ask.getId();
            // 新增提问跟章节的关联
            service.askAndChapter(chapterId,qid);
            // 根据进行新增关联表
            service.askAndCourse(cid, qid);
            // 新增用户和提问表的关联表
            service.askAndUser(qid, uid);
            //对用户提出问题的数量 进行学习记录的更新
            //判断用户是否收藏该课程，如果没有则只更新总数据
        UserCourseStudy userCourseStudy = serviceStudy.findCourseStudy(uid, cid);
        if (userCourseStudy!=null){
            //更新章节的提问记录
            service.updateChapterStudy(chapterId);
            //获取用户总学习记录
            Study study = serviceStudy.selectById(uid);
            //查询出用户所有的提问
            int num = serviceStudy.countQuestionNum(uid);
            //将用户所有的提问更新
            study.setQuestionNum(num);
            serviceStudy.handleUpdate(study);
            //获取用户在某课程提出的所有问题
            int courseAskNum = serviceStudy.courseStudyById(uid, cid);
            //将获取的提问数量更新
            userCourseStudy.setAskNum(courseAskNum);
            userCourseStudy.setCid(userCourseStudy.getId());
            serviceStudy.courseUpdate(userCourseStudy);
            return 0;

        }else {
            //更新总学习记录
            Study study = serviceStudy.selectById(uid);
            int num = serviceStudy.countQuestionNum(uid);
            study.setQuestionNum(num);
            serviceStudy.update(study);
            return 0;
        }
    }
//    回答
    @RequestMapping("/answer")
    public int answer(Ask ask){
       int i = service.answer(ask);
        return i;
    }
//    提问列表
    @RequestMapping("/askList")
    public List<Ask> askList(int id){
        List<Ask> list = service.askList(id);
        return list;
    }

    @RequestMapping("/questionList")
    public List<Ask> questionList(){
        List<Ask> list = service.questionList();
        return list;
    }
    //查询用户所提问的问题列表
    @RequestMapping("/userQuestions")
    public List<Ask> userQuestionList(int id){
        List<Ask> questionList = service.userQuestionList(id);
        return questionList;
    }
    //批量删除
    @RequestMapping("/deleteByIds")
    public int deleteByIds(Long[] ids){
        int i = service.deleteByIds(ids);
        return i;
    }
}
