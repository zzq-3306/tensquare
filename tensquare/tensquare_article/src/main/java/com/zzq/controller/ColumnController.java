package com.zzq.controller;

import com.zzq.model.Column;
import com.zzq.service.ColumnService;
import model.Result;
import model.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Zhang zq
 * @Date 2021/4/28 21:06
 * @Description
 */
@RestController
@RequestMapping("/column")
public class ColumnController {

    @Autowired
    private ColumnService columnService;


    /**
     * 添加栏目
     * @param column  栏目信息
     * @return          返回状态信息
     */
    @PostMapping
    public Result add(@RequestBody Column column){
        columnService.add(column);
        return new Result(true, StatusCode.OK,"成功");
    }

    /**
     * 查询全部
     * @return  返回结果集
     */
    @GetMapping
    public Result queryAll(){
        List<Column> list = columnService.queryAll();
        return new Result(true, StatusCode.OK,"成功",list);
    }

    /**
     * 根据id查询栏目信息
     * @param columnId  栏目的id
     * @return           返回id对应的信息
     */
    @GetMapping("/{columnId}")
    public Result queryById(@PathVariable String columnId){
        Column column = columnService.queryById(columnId);
        return new Result(true, StatusCode.OK,"成功",column);
    }

    /**
     * 修改栏目信息
     * @param columnId  栏目id
     * @param column    栏目信息
     * @return           返回状态信息
     */
    @PutMapping("/{columnId}")
    public Result updateById(@PathVariable String columnId,@RequestBody Column column){
        columnService.updateById(columnId,column);
        return new Result(true, StatusCode.OK,"成功");
    }

    /**
     * 根据栏目id删除
     * @param columnId   栏目id
     * @return            返回状态信息
     */
    @DeleteMapping("/{columnId}")
    public Result deleteById(@PathVariable String columnId){
        columnService.deleteById(columnId);
        return new Result(true, StatusCode.OK,"成功");
    }
}
