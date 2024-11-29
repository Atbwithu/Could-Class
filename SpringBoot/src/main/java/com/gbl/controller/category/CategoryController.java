package com.gbl.controller.category;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
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

        return service.select(category);
    }
    @RequestMapping("/getName")
    public List<Category> getName(){

        return service.getName();
    }
    @RequestMapping("/getChildrenName")
    public List<CategoryChildren> getChildrenName(int id){
        return service.getChildrenName(id);
    }
    //新增接口
    @RequestMapping("/insert")
    public int insert(Category category){
        return service.insert(category);
    }
    @RequestMapping("/delete")
    public int delete(int id){
        int i = service.delete(id);
        service.findDelChildId(id);
        return i;
    }
    @RequestMapping("/selectById")
    public Category selectById(int id){
        return service.selectById(id);
    }
    @RequestMapping("/update")
    public int update(Category category){
        return service.update(category);
    }
    /*子类接口*/
    @RequestMapping("/children")
    public List<CategoryChildren> findChildren(int id){
        redis.opsForValue().set("fatherId",String.valueOf(id));
        return service.findAllChild(id);
    }
    /*新增子类别接口*/
    @RequestMapping("/addChild")
    public int addChild(CategoryChildren children){
        String fatherId = redis.opsForValue().get("fatherId");
        service.addChild(children);
        int id = children.getId();
        assert fatherId != null;
        service.addRelation(Integer.parseInt(fatherId),id);
        return 0;

    }

    @RequestMapping("/findChildName")
    public List<CategoryChildren> childName(int id){
        return service.selectName(id);
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
        return service.deleteMany(ids);
    }
}
