package com.gbl.controller;

import com.gbl.entity.Category;
import com.gbl.entity.CategoryChildren;
import com.gbl.entity.SomeId;
import com.gbl.service.ICategoryService;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/category")
public class CategoryController {
    /*父类接口*/
    @Resource
    ICategoryService service;
    @Resource
    StringRedisTemplate redis;
    @RequestMapping("/list")
    public List<Category> select(Category category){
        List<Category> list = service.select(category);

        return list;
    }
    @RequestMapping("/getName")
    public List<Category> getName(){
        List<Category> list = service.getName();

        return list;
    }
    @RequestMapping("/getChildrenName")
    public List<CategoryChildren> getChildrenName(int id){
        List<CategoryChildren> list = service.getChildrenName(id);
        return list;
    }
    //新增接口
    @RequestMapping("/insert")
    public int insert(Category category){
        int i = service.insert(category);
        return i;
    }
    @RequestMapping("/delete")
    public int delete(int id){
        int i = service.delete(id);
        service.findDelChildId(id);
        return i;
    }
    @RequestMapping("/selectById")
    public Category selectById(int id){
        Category category =service.selectById(id);
        return category;
    }
    @RequestMapping("/update")
    public int update(Category category){
        int i =service.update(category);
        return i;
    }
    /*子类接口*/
    @RequestMapping("/children")
    public List<CategoryChildren> findChildren(int id){
        redis.opsForValue().set("fatherId",String.valueOf(id));
        List<CategoryChildren> allChild = service.findAllChild(id);
        return allChild;
    }
    /*新增子类别接口*/
    @RequestMapping("/addChild")
    public int addChild(CategoryChildren children){
        String fatherId = redis.opsForValue().get("fatherId");
        service.addChild(children);
        int id = children.getId();
        service.addRelation(Integer.valueOf(fatherId),id);
        return 0;

    }

    @RequestMapping("/findChildName")
    public List<CategoryChildren> childName(int id){
        List<CategoryChildren> categoryChildren = service.selectName(id);
        return categoryChildren;
    }
    @RequestMapping("/findChild")
    public List<CategoryChildren> findChild(){
        return service.selectChild();
    }
    //子类删除
    @RequestMapping("/delChild")
    public int delChild(int id){
        service.delChildOnly(id);
        return 0;
    }
    //预修改查询
    @RequestMapping("/selectChildren")
    public CategoryChildren selectChildren(int id){
        return service.selectChildren(id);
    }
    @RequestMapping("/updateChildren")
    public int selectChildren(CategoryChildren children){
        return service.updChildren(children);
    }
    //批量删除分类
    @RequestMapping("/deleteMany")
    public int deleteMany(Long[] ids){
        int i = service.deleteMany(ids);
        return i;
    }
}
