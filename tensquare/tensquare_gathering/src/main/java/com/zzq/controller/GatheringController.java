package com.zzq.controller;

import com.zzq.model.Gathering;
import com.zzq.service.GatheringService;
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
 * @Date 2021/4/28 9:47
 * @Description
 */
@RestController
@RequestMapping("/gathering")
public class GatheringController {

    @Autowired
    private GatheringService gatheringService;

    /**
     * 增加活动
     * @param gathering  活动详情信息
     * @return    返回状态信息
     */
    @PostMapping()
    public Result add(@RequestBody Gathering gathering){
        gatheringService.add(gathering);
        return new Result(true, StatusCode.OK,"成功");
    }

    /**
     * 活动全部列表
     * @return    返回结果集list
     */
    @GetMapping()
     public Result queryAll(){
           List<Gathering> list = gatheringService.queryAll();
          return new Result(true, StatusCode.OK,"成功",list);
     }

    /**
     * 根据id查询活动
      * @param gatheringId   活动id
     * @return               返回活动信息
     */
    @GetMapping("/{gatheringId}")
    public Result queryById(@PathVariable("gatheringId") String gatheringId){
      Gathering gathering = gatheringService.queryById(gatheringId);
      System.out.println("gathering = " + gathering);
      return new Result(true,StatusCode.OK,"成功",gathering);
    }

    /**
     * 修改活动
     * @param gatheringId   活动id
     * @return              返回状态信息
     */
    @PutMapping("/{gatheringId}")
    public Result updateById(@PathVariable String gatheringId,@RequestBody Gathering gathering){
        gatheringService.updateById(gatheringId,gathering);
        return new Result(true,StatusCode.OK,"成功");
    }

    /**
     * 根据活动id查询活动
     * @param gatheringId  活动id
     * @return              返回状态信息
     */
    @DeleteMapping("/{gatheringId}")
    public Result deleteById(@PathVariable String gatheringId){
        gatheringService.deleteById(gatheringId);
        return new Result(true,StatusCode.OK,"成功");
    }

    /**
     * 活动实体类
     * @param searchMap  搜索条件
     * @return           返回结果集
     */
    @PostMapping("/search")
    public Result search(@RequestBody Map searchMap){
        List<Gathering> list = gatheringService.search(searchMap);
        return new Result(true,StatusCode.OK,"成功",list);
    }

    /**
     * 活动分页
     * @param searchMap   搜素条件
     * @param page       当前页
     * @param size         页面大小
     * @return          返回结果集
     */
    @PostMapping("/search/{page}/{size}")
    public Result pageAndLike(@RequestBody Map searchMap,@PathVariable int page,@PathVariable int size){
        Page<Gathering> pageList = gatheringService.pageAndLike(searchMap,page,size);
        return new Result(true,StatusCode.OK,"成功",
                new PageResult<Gathering>(pageList.getTotalElements(),pageList.getContent()));
    }

    /**
     * 根据城市显示分页内容
     * @param city       城市
     * @param page       当前页
     * @param size       页面大小
     * @return           返回结果集
     */
    @GetMapping("/city/{city}/{page}/{size}")
    public Result cityageAndLike(@PathVariable String city,@PathVariable int page,@PathVariable int size){
        Page<Gathering> pageList = gatheringService.cityPageAndLike(city,page,size);
        return new Result(true,StatusCode.OK,"成功",
                new PageResult<Gathering>(pageList.getTotalElements(),pageList.getContent()));
    }
}
