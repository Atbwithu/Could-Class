package com.gbl.controller.askController;


import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gbl.entity.*;
import com.gbl.service.ask.IAskService;
import com.gbl.service.study.IStudyService;
import com.gbl.service.user.IUserService;
import com.gbl.utils.JwtTokenUtil;
import com.gbl.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/ask")
public class AskController {
    
    @Autowired
    IAskService service;
    @Autowired
    IUserService userService;
    @Autowired
    IStudyService serviceStudy;
    @Autowired
    StringRedisTemplate redis;
    
    private String username = JwtTokenUtil.getUsernameFromToken(redis.opsForValue().get("username"));

    
    @GetMapping("/list")
    public ResponseResult select(Ask ask) {
        return ResponseResult.success(service.list());
    }
    @RequestMapping("/ask")
    public ResponseResult insert(Ask ask){
        User user = userService.getOne(new QueryWrapper<User>().eq("username", username));
        ask.setUid(user.getId());
        return service.save(ask) ? ResponseResult.success() : ResponseResult.fail("提问失败");
    }
    @RequestMapping("/delete")
    public ResponseResult delete(int id){
        return service.removeById(id) ? ResponseResult.success() : ResponseResult.fail("删除失败");
    }
    @RequestMapping("/selectById")
    public ResponseResult selectById(int id){
        return ResponseResult.success(service.getById(id));
    }
    @RequestMapping("/reply")
    public ResponseResult reply(Ask ask){
        return ResponseResult.success(service.updateById(ask));
    }
    //提问列表
    @RequestMapping("/askList")
    public ResponseResult askList(int id){
        return service.askList(id);
    }

    @RequestMapping("/questionList")
    public ResponseResult questionList(){
        return service.questionList();
    }
    //查询用户所提问的问题列表
    @RequestMapping("/userQuestions")
    public ResponseResult userQuestionList(int id){
        return service.userQuestionList(id);
    }
    //批量删除
    @RequestMapping("/deleteByIds")
    public ResponseResult deleteByIds(Long[] ids){
        return service.deleteByIds(ids);
    }
}
