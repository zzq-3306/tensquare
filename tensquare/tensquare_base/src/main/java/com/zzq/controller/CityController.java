package com.zzq.controller;

import com.zzq.model.City;
import com.zzq.service.impl.CityService;
import model.PageResult;
import model.Result;
import model.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author Zhang zq
 * @Date 2021/4/23 10:22
 * @Description
 */
@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    /**
     * 查询全部城市信息
     * @return
     */
    @GetMapping()
    public Result queryAll(){
        return new Result(true, StatusCode.OK,"查询成功",cityService.queryAll());
    }

    /**
     * 根据城市id查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result queryById(@PathVariable String id){
        return new Result(true,StatusCode.OK,"查询成功",cityService.queryById(id));
    }

    /**
     * 添加城市信息
     * @param city 城市信息
     * @return   返回状态信息
     */
    @PostMapping()
    public Result add(@RequestBody City city){
        cityService.add(city);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /**
     * 修改城市信息
     * @param id  城市信息id
     * @param city  城市信息
     * @return    返回状态信息
     */
    @PutMapping("/{id}")
    public Result edit(@PathVariable("id") String id,@RequestBody City city){
        city.setId(id);
        cityService.modify(city);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /**
     * 根据城市id删除城市信息
     * @param id   城市id
     * @return   返回状态信息
     */
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable String id){
        cityService.deleteById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /**
     * 根据条件查询
     * @param searchMap  要查询的条件
     * @return   返回状态信息
     */
    @PostMapping("/search")
    public Result findSearch(@RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",cityService.findSearch(searchMap));
    }

    /**
     * 带分页的条件查询
     * @param searchMap   要查询的条件
     * @param page          当前页
     * @param size         页面的大小
     * @return           返回状态信息结果集
     */
    @PostMapping("/search/{page}/{size}")
    public Result findSearch(@RequestBody Map searchMap,@PathVariable int page,@PathVariable int size){
        Page<City> pageList = cityService.findSearch(searchMap, page, size);
        return new Result(true,StatusCode.OK,"查询成功",
                new PageResult<City>(pageList.getTotalElements(),pageList.getContent()));
    }
}
