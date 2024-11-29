package com.gbl.controller.kind;

import com.gbl.entity.Kinds;
import com.gbl.service.kind.IKindsService;
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
        return service.select(kinds);
    }
    @RequestMapping("/insert")
    public int insert(Kinds kinds){
        return service.insert(kinds);
    }
    @RequestMapping("/delete")
    public int delete(int id){
        return service.delete(id);
    }
    @RequestMapping("/selectById")
    public Kinds selectById(int id){
        return service.selectById(id);
    }
    @RequestMapping("/update")
    public int update(Kinds kinds){
        return service.update(kinds);
    }
}
