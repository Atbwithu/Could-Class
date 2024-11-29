package com.gbl.controller;

import com.gbl.entity.Kinds;
import com.gbl.service.IKindsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/kinds")
public class KindsController {
    @Resource
    IKindsService service;
    @RequestMapping("/list")
    public List<Kinds> select(Kinds kinds){
        List<Kinds> list = service.select(kinds);
        return list;
    }
    @RequestMapping("/insert")
    public int insert(Kinds kinds){
        int i = service.insert(kinds);
        return i;
    }
    @RequestMapping("/delete")
    public int delete(int id){
        int i = service.delete(id);
        return i;
    }
    @RequestMapping("/selectById")
    public Kinds selectById(int id){
        Kinds kinds =service.selectById(id);
        return kinds;
    }
    @RequestMapping("/update")
    public int update(Kinds kinds){
        int i =service.update(kinds);
        return i;
    }
}
