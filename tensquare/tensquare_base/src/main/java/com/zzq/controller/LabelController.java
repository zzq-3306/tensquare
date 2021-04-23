package com.zzq.controller;

import com.zzq.model.Label;
import com.zzq.service.LabelService;
import model.PageResult;
import model.Result;
import model.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author Zhang zq
 * @Date 2021/4/22 20:25
 * @Description
 */
@RestController
@RequestMapping("/label")
public class LabelController{

    @Autowired
    private LabelService labelService;


    /**
     * 查询全部列表
     * @return  返回结果list
     */
    @GetMapping()
    public  Result queryAll(){
        return new Result(true,StatusCode.OK,"查询成功",labelService.queryAll());
    }

    /**
     * 根据id查询标签
     * @param id  标签id
     * @return  返回id对应的标签信息
     */
    @GetMapping(value = "/{id}")
    public Result queryById(@PathVariable String id){
        return new Result(true,StatusCode.OK,"查询成功",labelService.queryById(id));
    }

    /**
     * 添加标签信息
     * @param label  标签信息
     * @return   返回状态信息
     */
    @PostMapping()
    public Result add(@RequestBody Label label){
        labelService.add(label);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /**
     * 修改标签信息
     * @param id  标签id
     * @param label  标签信息
     * @return   返回状态信息
     */
    @PutMapping("/{id}")
    public Result update(@PathVariable String id,@RequestBody Label label){
        label.setId(id);
        labelService.modify(label);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /**
     * 删除标签信息
     * @param id  标签id
     * @return   返回状态信息
     */
    @DeleteMapping("/{id}")
    public Result deleteByid(@PathVariable String id){
        labelService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @PostMapping("/search")
    public Result findSearch(@RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",labelService.findSearch(searchMap));
    }

    /**
     * 带分页的条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/search/{page}/{size}")
    public Result findSearch(@RequestBody Map searchMap,@PathVariable int page,@PathVariable int size){
        Page<Label> pageList = labelService.findSearch(searchMap, page, size);
        return new Result(true,StatusCode.OK,"查询成功",
                new PageResult<Label>(pageList.getTotalElements(),pageList.getContent()));
    }
}
