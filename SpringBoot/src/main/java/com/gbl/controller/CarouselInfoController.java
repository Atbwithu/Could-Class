package com.gbl.controller;

import com.gbl.entity.CarouselInfo;
import com.gbl.service.ICarouselInfoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/carouselInfo")
public class CarouselInfoController {
    @Resource
    ICarouselInfoService service;
    @RequestMapping("/list")
    public List<CarouselInfo> select(CarouselInfo carouselInfo){
        List<CarouselInfo> list = service.select(carouselInfo);
        return list;
    }
    @RequestMapping("/insert")
    public int insert(CarouselInfo carouselInfo){
        int i = service.insert(carouselInfo);
        return i;
    }
    @RequestMapping("/delete")
    public int delete(int id){
        int i = service.delete(id);
        return i;
    }
    @RequestMapping("/selectById")
    public CarouselInfo selectById(int id){
        CarouselInfo carouselInfo =service.selectById(id);
        return carouselInfo;
    }
    @RequestMapping("/update")
    public int update(CarouselInfo carouselInfo){
        int i =service.update(carouselInfo);
        return i;
    }
}
