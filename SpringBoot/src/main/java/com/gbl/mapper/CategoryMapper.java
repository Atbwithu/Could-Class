package com.gbl.mapper;


import com.gbl.entity.Category;
import com.gbl.entity.CategoryChildren;
import com.gbl.entity.SomeId;
import com.gbl.service.IChapterService;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: 哥布林酋长
 * @Description: 分类表-接口
 * @Date: 2024/5/1 15:23
 * @Version: 1.0
 */
@Mapper
public interface CategoryMapper  {
    /**
     *
     * 父类的类别mapper
     */
    //    增 删 改 查
    int insert(Category category);
    //单个删除
    int delete(int id);
    Category selectById(int id);
    //查询关联的id类别
    List<SomeId> findDelChildId(int id);
    //删除子类别
    int delChildren(List<SomeId> ids);
    int update(Category category);
    List<Category> select(Category category);
    //查询类别
    List<Category> getName();
    List<CategoryChildren> getChildrenName(int id);
    //批量删除父类别
    int deleteMany(Long[] ids);
    //根据数组查询父类关联子类id
    Long[] findAllFatherId(Long[] ids);
    //删除父类跟子类的数据
    int deleteFatherChildren(Long[] ids);
    //删除子类的多条数据
    int deleteManyChildren(Long[] ids);



    /**
     * 子类mapper
     */

    //新增
    int addChild(CategoryChildren children);
    //新增关联
    int addRelation(int fatherId ,int childId);
    //删除
    int delChildOnly(int id);
    //子类表删除删除关联表
    int delRelation(int id);
    //查询一个
    Category findChildById(int id);
    //修改
    int updChild(CategoryChildren children);
    //查询
    List<CategoryChildren> findAllChild(int id);
    //前台查询子类的名字
    List<CategoryChildren> selectName(int id);
    //查全部子类
    List<CategoryChildren> selectChild();
    //查询一个子类
    CategoryChildren selectChildren(int id);
    //修改子类
    int updChildren(CategoryChildren children);
    //新增父类别跟课程的关联
    int fatherWithCourse(int cid,int fid);
    //新增子类别跟课程的关联
    int kidWithCourse(int cid,int fid);
    //课程新增时分配类别
    int forFather(int fid,int cid);
    int forChild(int kid,int cid);

}
