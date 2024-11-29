package com.gbl.service.impl;

import com.gbl.entity.Category;
import com.gbl.entity.CategoryChildren;
import com.gbl.entity.SomeId;
import com.gbl.mapper.CategoryMapper;
import com.gbl.service.ICategoryService;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author: Insight
 * @Description: 问答
 * @Date: 2024/5/1 15:37
 * @Version: 1.0
 */
@Service
public class CategoryServiceImpl implements ICategoryService {
    @Resource
    CategoryMapper mapper;
    @Override
    public int insert(Category category) {
        SimpleDateFormat sdf = new SimpleDateFormat("yy:MM:dd hh:mm:ss");
        Date date = new Date();
        String format = sdf.format(date);
        category.setCreateTime(format);
        category.setParent(0);
        Integer sort = (Integer) category.getSort();
        if (sort instanceof Integer) {
            return mapper.insert(category);
        }else {
            return -1;
        }

    }

    @Override
    public int delete(int id) {
        return mapper.delete(id);
    }
    //删除父类时删除所有子类
    @Override
    public List<SomeId> findDelChildId(int id) {
        List<SomeId> childId = mapper.findDelChildId(id);
        if (!childId.isEmpty()) {
            mapper.delChildren(childId);
        }

        return null;
    }



    @Override
    public Category selectById(int id) {
        return mapper.selectById(id);
    }

    @Override
    public int update(Category category) {
        return mapper.update(category);
    }

    @Override
    public List<Category> select(Category category) {
        return mapper.select(category);
    }

    @Override
    public List<Category> getName() {
        return mapper.getName();
    }

    @Override
    public List<CategoryChildren> getChildrenName(int id) {
        return mapper.getChildrenName(id);
    }

    @Override
    public int addChild(CategoryChildren children) {
        SimpleDateFormat sdf = new SimpleDateFormat("yy:MM:dd hh:mm:ss");
        Date date = new Date();
        String format = sdf.format(date);
        children.setCreateTime(format);
        return mapper.addChild(children);
    }

    @Override
    public int addRelation(int fatherId, int childId) {
        return mapper.addRelation(fatherId,childId);
    }

    @Override
    public int delChild(int id) {
        return mapper.delChildOnly(id);
    }

    @Override
    public int delChildOnly(int id) {
        mapper.delChildOnly(id);
        mapper.delRelation(id);
        return 1;
    }

    @Override
    public Category findChildById(int id) {
        return mapper.findChildById(id);
    }

    @Override
    public int updChild(CategoryChildren children) {
        return updChild(children);
    }

    @Override
    public List<CategoryChildren> findAllChild(int id) {
        return mapper.findAllChild(id);
    }

    @Override
    public List<CategoryChildren> selectName(int id) {
        return mapper.selectName(id);
    }

    @Override
    public List<CategoryChildren> selectChild() {
        return mapper.selectChild();
    }

    @Override
    public CategoryChildren selectChildren(int id) {
        return mapper.selectChildren(id);
    }

    @Override
    public int updChildren(CategoryChildren children) {
        return mapper.updChildren(children);
    }

    @Override
    public void addRelationShip(int cid, int fid, int kid) {
        if (cid<=0 && fid<=0 && kid<=0){
            mapper.fatherWithCourse(cid,fid);
            mapper.kidWithCourse(cid,kid);
        }
    }

    @Override
    public int deleteMany(Long[] ids) {
        Long[] allFatherId = mapper.findAllFatherId(ids);
        if (allFatherId.length == 0){
            mapper.deleteMany(ids);
            return -1;
        }else {
            //批量删除父类别
            mapper.deleteMany(ids);
            //删除父类跟子类的数据
            mapper.deleteFatherChildren(ids);
            //删除子类的多条数据
            mapper.deleteManyChildren(allFatherId);
            return 0;
        }

    }

    @Override
    public int forCourseKind(int cid, int fid, int kid) {
        //判断传值是否为空
        if (cid<=0){
            return -1;
        }else if (fid <=0){
            return -2;
        }else if (kid<=0){
            return -3;
        }else {
            mapper.forFather(fid,cid);
            mapper.forChild(kid,cid);
            return 0;
        }

    }


}
