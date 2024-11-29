package com.gbl.service.impl;



import com.gbl.entity.Ask;
import com.gbl.entity.ChapterStudy;
import com.gbl.mapper.AskMapper;
import com.gbl.service.IAskService;

import com.github.pagehelper.PageHelper;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;


import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Author: Insight
 * @Description: 问答
 * @Date: 2024/5/1 15:37
 * @Version: 1.0
 */
@Service
public class AskServiceImpl implements IAskService {
    @Resource
    AskMapper mapper;
    @Resource
    StringRedisTemplate redis;
    @Override
    public int insert(Ask ask) {
        return mapper.insert(ask);
    }

    @Override
    public int askAndCourse(int cid, int qid) {
        return mapper.askAndCourse(cid,qid);
    }

    @Override
    public int askAndUser(int qid, int uid) {
        return mapper.askAndUser(qid,uid);
    }

    @Override
    public int delete(int id) {
        return mapper.delete(id);
    }

    @Override
    public Ask selectById(int id) {
        return mapper.selectById(id);
    }

    @Override
    public int update(Ask ask) {
        return mapper.update(ask);
    }

    @Override
    public List<Ask> select(Ask ask) {
        return mapper.select(ask);
    }


//    @Override
//    public Page<Ask> selectPage(int pageNum,int pageSize) {
//        Page<Ask> page = new Page<>(pageNum,pageSize);
//        PageHelper.startPage(pageNum,pageSize);
//        Page<Ask> askPage = page.setRecords(this.list(page));
//        return askPage.getRecords();
//    }

    @Override
    public int ask(Ask ask) {
        SimpleDateFormat sdf  =  new  SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = new Date();
        String time = sdf.format(date);
        ask.setCreateTime(time);
        return mapper.ask(ask);
    }

    @Override
    public int answer(Ask ask) {
        return mapper.answer(ask);
    }

    @Override
    public List<Ask> askList(int id) {
        return mapper.askList(id);
    }

    @Override
    public int askAndChapter(int chapterId, int qid) {
        mapper.askAndChapter(chapterId,qid);
        return 0;
    }


    @Override
    public List<Ask> questionList() {
        return mapper.questionList();
    }

    @Override
    public List<Ask> userQuestionList(int id) {
        return mapper.userQuestionList(id);
    }

    @Override
    public int deleteByIds(Long[] ids) {
        int i = mapper.deleteByIds(ids);
        mapper.delRelationByIds(ids);
        return 0;
    }

    @Override
    public int updateChapterStudy(int cid) {
        ChapterStudy chapterStudy = new ChapterStudy(); //章节学习记录的对象
        int num = mapper.findAskByChapter(cid);
        chapterStudy.setAskNum(num);
        chapterStudy.setId(cid);
        return mapper.updateChapterStudy(chapterStudy);
    }


}
