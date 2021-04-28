package com.zzq.controller;

import com.zzq.service.FriendService;
import model.Result;
import model.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Zhang zq
 * @Date 2021/4/27 17:20
 * @Description
 */
@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private FriendService friendService;

    /**
     * 添加好友或者非好友
     * @param friendid  友好或非好友的id
     * @param type      1 喜欢      2不喜欢
     * @return      返回状态信息
     */
    @PutMapping("/like/{friendid}/{type}")
    public Result add(@PathVariable String friendid,@PathVariable String type){
        int i = friendService.add(friendid,type);
        if (i > 0){
            return new Result(true, StatusCode.OK,"successful operation");
        }
        return new Result(false, StatusCode.ERROR,"fail operation");
    }

    /**
     * 删除好友
     * @param friendid  好友的id
     * @return      返回状态信息
     */
    @DeleteMapping("/{friendid}")
    public Result delete(@PathVariable String friendid){
        int i = friendService.deleteByFriendId(friendid);
        if (i > 0){
            return new Result(true, StatusCode.OK,"操作成功");
        }
        return new Result(false, StatusCode.ERROR,"操作失败");
    }

}
