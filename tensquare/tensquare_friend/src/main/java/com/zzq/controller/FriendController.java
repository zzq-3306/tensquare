package com.zzq.controller;

import com.zzq.service.FriendService;
import io.jsonwebtoken.Claims;
import model.Result;
import model.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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

   @Autowired
    private HttpServletRequest request;

   @PutMapping("/like/{friendid}/{type}")
   public Result addFriend(@PathVariable String friendid,@PathVariable String type){
       Claims claims = (Claims) request.getAttribute("user_claims");
       if(claims == null){
           return new Result(false,StatusCode.ACCESSERROR,"无权访问");
       }
       //如果喜欢
       if("type".equals("1")){
           if(friendService.addFriend(claims.getId(),friendid)==0){
               return new Result(false,StatusCode.PREERROR,"已经添加此好友");
           }
       }else{
           //不喜欢

       }
       return new Result(true,StatusCode.OK,"操作成功");
   }

}
