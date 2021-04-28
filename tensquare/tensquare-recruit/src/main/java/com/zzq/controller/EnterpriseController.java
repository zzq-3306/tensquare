package com.zzq.controller;

import com.zzq.model.Enterprise;
import com.zzq.service.EnterpriseService;
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
 * @Date 2021/4/28 16:56
 * @Description
 */
@RestController
@RequestMapping("/enterprise")
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseService;

    /**
     * 增加企业
     * @param enterprise 企业信息
     * @return    返回状态结果
     */
    @PostMapping()
    public Result add(@RequestBody Enterprise enterprise){
        enterpriseService.add(enterprise);
        return new Result(true, StatusCode.OK,"成功");
    }

    /**
     * 企业全部列表
     * @return  返回结果集
     */
    @GetMapping()
    public Result queryAll(){
        List<Enterprise> list = enterpriseService.queryAll();
        return new Result(true,StatusCode.OK,"成功",list);
    }

    /**
     * 根据id查询企业
     * @param enterpriseId  企业的id
     * @return      返回企业的信息
     */
    @GetMapping("/{enterpriseId}")
    public Result queryAll(@PathVariable String enterpriseId){
        Enterprise enterprise = enterpriseService.queryById(enterpriseId);
        return new Result(true,StatusCode.OK,"成功",enterprise);
    }

    /**
     * 修改企业
     * @param enterpriseId  企业的id
     * @return          返回状态信息
     */
    @PutMapping("/{enterpriseId}")
    public Result updateById(@PathVariable String enterpriseId,@RequestBody Enterprise enterprise){
        enterpriseService.updateById(enterpriseId,enterprise);
        return new Result(true,StatusCode.OK,"成功");
    }

    /**
     * 根据id删除企业
     * @param enterpriseId  企业的id
     * @return       返回状态的信息
     */
    @DeleteMapping("/{enterpriseId}")
    public Result deleteById(@PathVariable String enterpriseId){
        enterpriseService.deleteById(enterpriseId);
        return new Result(true,StatusCode.OK,"成功");
    }

    /**
     * 根据条件查询企业列表
     * @param enterprise  搜索条件
     * @return        返回结果集
     */
    @PostMapping("/search")
    public Result search(@RequestBody Enterprise enterprise){
        List<Enterprise> list = enterpriseService.search(enterprise);
        return new Result(true,StatusCode.OK,"成功",list);
    }

    /**
     * 热门企业列表
     * @return   返回结果集
     */
    @GetMapping("/search/hotlist")
    public Result hotList(){
        List<Enterprise> list = enterpriseService.hotList();
        return new Result(true,StatusCode.OK,"成功",list);
    }

    /**
     * enterprise 分页模糊查询
     * @param enterprise  搜索条件
     * @param page        当前页
     * @param size        页面大小
     * @return            返回结果集
     */
    @PostMapping("/search/{page}/{size}")
    public Result pageAndLike(@RequestBody Enterprise enterprise,@PathVariable int page,@PathVariable int size){
        Page<Enterprise> pageList = enterpriseService.pageAndLike(enterprise,page,size);
        return new Result(true,StatusCode.OK,"查询成功",
                new PageResult<Enterprise>(pageList.getTotalElements(),pageList.getContent()));
    }
}
