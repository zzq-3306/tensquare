package com.zzq.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzq.model.Spit;
import com.zzq.service.SpitService;
import model.PageResult;
import model.Result;
import model.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import util.JedisPoolUtil;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Author Zhang zq
 * @Date 2021/4/27 11:25
 * @Description
 */
@RestController
@RequestMapping("/spit")
public class SpitController {

    @Autowired
    private SpitService spitService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 增加吐槽
     * @param spit  吐槽的信息
     * @return        返回状态的信息
     */
    @PostMapping()
    public Result add(@RequestBody Spit spit){
        spitService.add(spit);
        return new Result(true, StatusCode.OK,"成功");
    }

    /**
     * spit全部列表
     * @return  返回结果集
     */
    @GetMapping()
    public Result queryAll(){
        List<Spit> list = spitService.queryAll();
        return new Result(true,StatusCode.OK,"成功",list);
    }

    /**
     * 根据id查询吐槽
     * @return  返回结果
     */
    @GetMapping("/{spitId}")
    public Result queryById(@PathVariable String spitId){
        Spit spit = spitService.queryById(spitId);
        return new Result(true,StatusCode.OK,"成功",spit);
    }

    /**
     * 修改吐槽
     * @param spit  吐槽信息
     * @param spitId  吐槽id
     * @return     返回状态信息
     */
    @PutMapping("/{spitId}")
    public Result updateById(@RequestBody Spit spit,@PathVariable String spitId){
        spitService.updateById(spitId,spit);
        return new Result(true,StatusCode.OK,"成功",spit);
    }

    /**
     * 根据id删除吐槽
     * @param spitId  吐槽id
     * @return      返回状态信息
     */
    @DeleteMapping("/{spitId}")
    public Result deleteById(@PathVariable String spitId){
        spitService.deleteById(spitId);
        return new Result(true,StatusCode.OK,"成功");
    }

    /**
     * 吐槽点赞
     * @param spitId  吐槽的Id
     * @return       返回状态信息
     */
    @PutMapping("/thumbup/{spitId}")
    public Result thumbupMethod(@PathVariable String spitId){
        String id = "";
        //当前登陆的用户
        Jedis jedis = JedisPoolUtil.getJedis();
        String loginUser = jedis.get("loginUser");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map map = objectMapper.readValue(loginUser, Map.class);
            //当前登陆用户的id
             id = (String) map.get("id");
            System.out.println(id+"==========");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (redisTemplate.opsForValue().get("thumbup_"+id+"_"+spitId) != null){
            return new Result(false,StatusCode.ERROR,"你已经点过赞了");
        }
        spitService.updateThumbup(spitId);
        //防止重复点赞
        redisTemplate.opsForValue().set("thumbup_"+id+"_"+spitId,"1");
        return new Result(true,StatusCode.OK,"成功");
    }


    /**
     * 根据条件搜索spit列表
     * @param spit    条件
     * @return      返回结果信息
     */
    @PostMapping("/search")
    public Result search(@RequestBody Spit spit){
        List<Spit> list = spitService.search(spit);
        return new Result(true,StatusCode.OK,"成功",list);
    }

    /**
     * spit分页
     * @param spit  查询条件
     * @param page       当前页
     * @param size       页面大小
     * @return         返回结果集
     */
    @PostMapping("/search/{page}/{size}")
    public Result findPageAndLike(@RequestBody Spit spit,@PathVariable int page,@PathVariable int size){
        Page<Spit> list = spitService.findPageAndLike(spit,page,size);
        return new Result(true,StatusCode.OK,"查询成功",
                new PageResult<Spit>(list.getTotalElements(),list.getContent()));
    }

    /**
     * 根据上级id查询吐槽信息
     * @param parentid   上级吐槽id
     * @param page       当前页
     * @param size          页面大小
     * @return            返回结果集
     */
    @GetMapping("/comment/{parentid}/{page}/{size}")
    public Result findByParentId(@PathVariable String parentid,@PathVariable int page,@PathVariable int size){
        Page<Spit> list = spitService.findByParentId(parentid,page,size);
        return new Result(true,StatusCode.OK,"查询成功",
                new PageResult<Spit>(list.getTotalElements(),list.getContent()));
    }


}
