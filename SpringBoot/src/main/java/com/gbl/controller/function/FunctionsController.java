package com.gbl.controller.function;

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
        return service.select(functions);
    }
    @RequestMapping("/insert")
    public int insert(Functions functions){
        return service.insert(functions);
    }
    @RequestMapping("/delete")
    public int delete(int id){
        return service.delete(id);
    }
    @RequestMapping("/selectById")
    public Functions selectById(int id){
        return service.selectById(id);
    }
    @RequestMapping("/update")
    public int update(Functions functions){
        return service.update(functions);
    }
}
