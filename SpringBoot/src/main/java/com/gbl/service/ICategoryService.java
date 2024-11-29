package com.gbl.service;


import com.gbl.entity.Category;
import com.gbl.entity.CategoryChildren;
import com.gbl.entity.SomeId;
import com.gbl.entity.Video;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: Insight
 * @Description: TODO
 * @Date: 2024/5/1 15:33
 * @Version: 1.0
 */
public interface ICategoryService{
    //    增 删 改 查
    int insert(Category category);
    /*
        删除功能
        删除类别子类别全删
     */
    @Transactional
    int delete(int id);
    //查询关联的id类别
    List<SomeId> findDelChildId(int id);

    Category selectById(int id);
    @Transactional
    int update(Category category);
    List<Category> select(Category category);
    //查询类别
    List<Category> getName();
    List<CategoryChildren> getChildrenName(int id);

    /**
     * 子类mapper
     */

    //新增
    @Transactional
    int addChild(CategoryChildren children);
    @Transactional
    int addRelation(int fatherId ,int childId);
    //删除
    @Transactional
    int delChild(int id);
    //删除单个
    @Transactional
    int delChildOnly(int id);
    //查询一个
    Category findChildById(int id);
    //修改
    @Transactional
    int updChild(CategoryChildren children);


    List<CategoryChildren> findAllChild(int id);
    //前台查询子类的名字
    List<CategoryChildren> selectName(int id);
    //查全部子类
    List<CategoryChildren> selectChild();
    //查询一个子类
    CategoryChildren selectChildren(int id);
    //修改子类
    @Transactional
    int updChildren(CategoryChildren children);
    //视频上传功能
//    int uploadVideo(String videoName,String savePath);
    //课程时新增子类别和父类别跟课程的关联
    void addRelationShip(int cid,int fid,int kid);
    //批量删除父类
    int deleteMany(Long[] ids);
    //课程新增时分配类别
    int forCourseKind(int cid,int fid,int kid);
}
