package com.zzq.controller;

import com.zzq.model.Problem;
import com.zzq.service.ProblemService;
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
 * @Date 2021/4/26 12:03
 * @Description
 */
@RestController
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    /**
     * 添加问题
     * @param problem  问题信息
     * @return       返回状态信息
     */
    @PostMapping()
    public Result addProblem(@RequestBody Problem problem){
        problemService.addProblem(problem);
        return new Result(true, StatusCode.OK,"成功");
    }

    /**
     * 查询全部
     * @return  返回结果list
     */
    @GetMapping()
    public Result getAll(){
        List<Problem> list = problemService.findAll();
        return new Result(true,StatusCode.OK,"成功",list);
    }

    /**
     * 根据id查询问题
     * @param problemId  问题的Id
     * @return     返回信息
     */
    @GetMapping("/{problemId}")
    public Result queryById(@PathVariable String problemId){
        Problem problem = problemService.findById(problemId);
        return new Result(true,StatusCode.OK,"成功",problem);
    }

    /**
     * 根据id修改问题
     * @param problemId  问题的id
     * @param problem      问题的信息
     * @return      返回状态信息
     */
    @PutMapping("/{problemId}")
    public Result update(@PathVariable String problemId,@RequestBody Problem problem){
        problem.setId(problemId);
        problemService.updateById(problem);
        return new Result(true,StatusCode.OK,"成功");
    }

    /**
     * 根据id删除信息
     * @param problemId  问题id
     * @return     返回状态信息
     */
    @DeleteMapping("/{problemId}")
    public Result deleteId(@PathVariable String problemId){
        problemService.deleteById(problemId);
        return new Result(true,StatusCode.OK,"成功");
    }

    /**
     * 根据条件查询问题列表
     * @param searchMap 搜索条件
     * @return      返回问题列表
     */
    @PostMapping("/search")
    public Result search(@RequestBody Map searchMap){
        List<Problem> list = problemService.search(searchMap);
        return new Result(true,StatusCode.OK,"成功",list);
    }

    /**
     * 问题查询和分页
     * @param searchMap  搜索条件
     * @param page      当前页
     * @param size       页面大小
     * @return     返回状态结果集
     */
    @PostMapping("/search/{page}/{size}")
    public Result queryPageAndLike(@RequestBody Map searchMap,@PathVariable int page,@PathVariable int size){
        Page<Problem> problemPage = problemService.queryPageAndLike(searchMap,page,size);
        return new Result(true,StatusCode.OK,"查询成功",
                new PageResult<Problem>(problemPage.getTotalElements(),problemPage.getContent()));
    }

    /**
     * 最新问答列表
     * @param label     标签Id  为0则查询全部
     * @param page       当前页
     * @param size       页面大小
     * @return           返回结果
     */
    @GetMapping("/newlist/{label}/{page}/{size}")
    public Result newList(@PathVariable String label,@PathVariable int page,@PathVariable int size){
        Page<Problem> list = problemService.findNewListByLabelId(label,page,size);
        return new Result(true,StatusCode.OK,"查询成功",
                new PageResult<Problem>(list.getTotalElements(),list.getContent()));
    }

    /**
     * 热门问答列表
     * @param label     标签Id  为0则查询全部
     * @param page       当前页
     * @param size       页面大小
     * @return           返回结果
     */
    @GetMapping("/hotlist/{label}/{page}/{size}")
    public Result hotlist(@PathVariable String label,@PathVariable int page,@PathVariable int size){
        Page<Problem> list = problemService.findHotListByLabelId(label,page,size);
        return new Result(true,StatusCode.OK,"查询成功",
                new PageResult<Problem>(list.getTotalElements(),list.getContent()));
    }

    /**
     * 等待回答列表
     * @param label  标签id
     * @param page    当前页
     * @param size     页面大小
     * @return          返回状态结果
     */
    @GetMapping("/waitlist/{label}/{page}/{size}")
    public Result waitList(@PathVariable String label,@PathVariable int page,@PathVariable int size){
        Page<Problem> list = problemService.findWaitListByLabelId(label,page,size);
        return new Result(true,StatusCode.OK,"查询成功",
                new PageResult<Problem>(list.getTotalElements(),list.getContent()));
    }

    /**
     * Problem分页
     * @param label  标签Id
     * @param page     当前页
     * @param size    大小
     * @return       返回状态
     */
    @PostMapping("/all/{label}/{page}/{size}")
    public Result querAll(@PathVariable String label,@PathVariable int page,@PathVariable int size){
        Page<Problem> list = problemService.queryPage(label,page,size);
        return new Result(true,StatusCode.OK,"查询成功",
                new PageResult<Problem>(list.getTotalElements(),list.getContent()));
    }
}
