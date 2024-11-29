package com.gbl.service;

import com.gbl.entity.Ask;
import com.gbl.entity.ChapterStudy;
import com.gbl.entity.SomeId;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.List;

/**
 * @Author: Insight
 * @Description: TODO
 * @Date: 2024/5/1 15:33
 * @Version: 1.0
 */
public interface IAskService {
    //    增 删 改 查
    @Transactional
    int insert(Ask ask);
    //新增问题关联的用户表
    @Transactional
    int askAndCourse(int cid,int qid);
    //新增问题关联的用户表
    @Transactional
    int askAndUser(int qid,int uid);
    @Transactional
    int delete(int id);
    Ask selectById(int id);
    @Transactional
    int update(Ask ask);
    List<Ask> select(Ask ask);
//    List<Ask> selectPage(int pageNum,int pageSize);

    //    提问
    @Transactional
    int ask(Ask ask);
//    回答
    @Transactional
    int answer(Ask ask);
    //    章节提问列表
    List<Ask> askList(int id);
    //    新增问题跟章节的关联
    int askAndChapter(int chapterId,int qid);
    List<Ask> questionList();
    List<Ask> userQuestionList(int id);
    //批量删除
    int deleteByIds(Long[] ids);
    //更新章节的学习记录
    int updateChapterStudy(int cid);

}
