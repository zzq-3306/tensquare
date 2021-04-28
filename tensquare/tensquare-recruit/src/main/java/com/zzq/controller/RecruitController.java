package com.zzq.controller;

import com.zzq.model.Recruit;
import com.zzq.service.RecruitService;
import model.Result;
import model.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Zhang zq
 * @Date 2021/4/28 16:44
 * @Description
 */
@RestController
@RequestMapping("/recruit")
public class RecruitController {

    @Autowired
    private RecruitService recruitService;

    /**
     * 增加招聘
     * @param recruit    招聘信息
     * @return           返回状态信息
     */
    @PostMapping()
    public Result add(@RequestBody Recruit recruit){
        System.out.println("recruit = " + recruit);
        recruitService.add(recruit);
        return new Result(true, StatusCode.OK,"成功");
    }

    /**
     * 招聘全部列表
     * @return  返回全部列表
     */
    @GetMapping()
    public Result queryAll(){
       List<Recruit> list  = recruitService.queryAll();
        return new Result(true, StatusCode.OK,"成功",list);
    }

    /**
     * 根据id查询招聘
     * @param recruitId  招聘id
     * @return         返回结果
     */
    @GetMapping("/{recruitId}")
    public Result queryAll(@PathVariable String recruitId){
        Recruit recruit  = recruitService.queryById(recruitId);
        return new Result(true, StatusCode.OK,"成功",recruit);
    }

    /**
     * 修改招聘
     * @param recruit   招聘信息
     * @param recruitId   招聘id
     * @return          返回状态信息
     */
    @PutMapping("/{recruitId}")
    public Result updateById(@RequestBody Recruit recruit,@PathVariable String recruitId){
        recruitService.updateById(recruit,recruitId);
        return new Result(true, StatusCode.OK,"成功");
    }

    /**
     * 根据id删除招聘信息
     * @param recruitId  招聘id
     * @return         返回状态信息
     */
    @DeleteMapping("/{recruitId}")
    public Result deleteById(@PathVariable String recruitId){
        recruitService.deleteById(recruitId);
        return new Result(true, StatusCode.OK,"成功");
    }

    /**
     * 根据条件查询招聘列表
     * @param recruit   条件
     * @return         返回结果list
     */
    @PostMapping("/search")
    public Result search(@RequestBody Recruit recruit){
        List<Recruit> list = recruitService.search(recruit);
        return new Result(true, StatusCode.OK,"成功",list);
    }

    /**
     * 招聘分页
     * @param recruit  搜索条件
     * @param page     当前页
     * @param size     页面大小
     * @return         返回结果list
     */
    @PostMapping("/search/{page}/{size}")
    public Result pageAndLike(@RequestBody Recruit recruit,@PathVariable int page,@PathVariable int size){
        Page<Recruit> list = recruitService.pageAndLike(recruit,page,size);
        return new Result(true, StatusCode.OK,"成功",list);
    }

    /**
     * 推荐职位
     * @return   返回结果list
     */
    @GetMapping("/search/recommend")
    public Result recommend(){
        List<Recruit> list = recruitService.recommend();
        return new Result(true, StatusCode.OK,"成功",list);
    }

    /**
     * 最新职位
     * @return  返回结果list
     */
    @GetMapping("/search/newlist")
    public Result newList(){
        List<Recruit> list = recruitService.newList();
        return new Result(true, StatusCode.OK,"成功",list);
    }

}
