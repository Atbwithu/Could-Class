package com.gbl.controller;

import com.gbl.entity.Functions;
import com.gbl.service.IFunctionsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/functions")
public class FunctionsController {
    @Resource
    IFunctionsService service;
    @RequestMapping("/list")
    public List<Functions> select(Functions functions){
        List<Functions> list = service.select(functions);
        return list;
    }
    @RequestMapping("/insert")
    public int insert(Functions functions){
        int i = service.insert(functions);
        return i;
    }
    @RequestMapping("/delete")
    public int delete(int id){
        int i = service.delete(id);
        return i;
    }
    @RequestMapping("/selectById")
    public Functions selectById(int id){
        Functions functions =service.selectById(id);
        return functions;
    }
    @RequestMapping("/update")
    public int update(Functions functions){
        int i =service.update(functions);
        return i;
    }
}
