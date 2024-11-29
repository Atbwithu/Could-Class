package com.gbl.mapper;

import com.gbl.entity.Ask;
import com.gbl.entity.ChapterStudy;
import com.gbl.entity.SomeId;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: 哥布林酋长
 * @Description: 问答表-接口
 * @Date: 2024/5/1 15:23
 * @Version: 1.0
 */
@Mapper
public interface AskMapper {
    //    增 删 改 查
    int insert(Ask ask);

    //新增问题关联的用户表
    int askAndCourse(int cid,int qid);
    //新增问题关联的用户表
    int askAndUser(int qid,int uid);
    int delete(int id);
    Ask selectById(int id);
    int update(Ask ask);
    List<Ask> select(Ask ask);
//    提问
    int ask(Ask ask);
//    回答
    int answer(Ask ask);
//    章节提问列表
    List<Ask> askList(int id);
//    新增问题跟章节的关联
    int askAndChapter(int chapterId,int qid);

    List<Ask> questionList();
    List<Ask> userQuestionList(int id);
    //批量删除
    int deleteByIds(Long[] ids);
    //批量删除问题跟用户关联的表
    int delRelationByIds(Long[] ids);
    //更新章节的学习记录
    int updateChapterStudy(ChapterStudy chapterStudy);
    //查询所有的章节下的提问
    int findAskByChapter(int id);

}
