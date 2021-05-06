package com.zzq.controller;

import com.zzq.model.Channel;
import com.zzq.service.ChannelService;
import model.Result;
import model.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Scanner;

/**
 * @Author Zhang zq
 * @Date 2021/4/28 21:06
 * @Description
 */
@RestController
@RequestMapping("/channel")
public class ChannelController {

    @Autowired
    private ChannelService channelService;


    /**
     * 添加频道
     * @param channel  频道信息
     * @return          返回状态信息
     */
    @PostMapping
    public Result add(@RequestBody Channel channel){
        channelService.add(channel);
        return new Result(true, StatusCode.OK,"成功");
    }

    /**
     * 查询全部
     * @return  返回结果集
     */
    @GetMapping
    public Result queryAll(){
        List<Channel> list = channelService.queryAll();
        return new Result(true, StatusCode.OK,"成功",list);
    }

    /**
     * 根据id查询频道信息
     * @param channelId  频道的id
     * @return           返回id对应的信息
     */
    @GetMapping("/{channelId}")
    public Result queryById(@PathVariable String channelId){
        Channel channel = channelService.queryById(channelId);
        return new Result(true, StatusCode.OK,"成功",channel);
    }

    /**
     * 修改频道信息
     * @param channelId  频道id
     * @param channel    频道信息
     * @return           返回状态信息
     */
    @PutMapping("/{channelId}")
    public Result updateById(@PathVariable String channelId,@RequestBody Channel channel){
        channelService.updateById(channelId,channel);
        return new Result(true, StatusCode.OK,"成功");
    }

    /**
     * 根据频道id删除
     * @param channelId   频道id
     * @return            返回状态信息
     */
    @DeleteMapping("/{channelId}")
    public Result deleteById(@PathVariable String channelId){
        channelService.deleteById(channelId);
        return new Result(true, StatusCode.OK,"成功");
    }
}
