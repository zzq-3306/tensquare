package com.zzq.controller;

import com.zzq.model.Login;
import com.zzq.model.User;
import com.zzq.service.UserService;
import model.Result;
import model.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.TimeCastHandler;

/**
 * @Author Zhang zq
 * @Date 2021/4/23 14:58
 * @Description
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 添加用户
     * @param user 用户信息
     * @return   状态信息
     */
    @PostMapping()
    public Result add(@RequestBody User user){
        System.out.println("user = " + user);

        if (user.getBirthday()!=null && !"".equals(user.getBirthday())){
            user.setBirthday(TimeCastHandler.strCastdateTime(user.getBirthday()));
        }
        if (user.getRegdate()!=null && !"".equals(user.getRegdate())){
            user.setRegdate(TimeCastHandler.strCastdateTime(user.getRegdate()));
        }
        if (user.getUpdateddate()!=null && !"".equals(user.getUpdateddate())){
            user.setUpdateddate(TimeCastHandler.strCastdateTime(user.getUpdateddate()));
        }
        if (user.getLastdate()!=null && !"".equals(user.getLastdate())){
            user.setLastdate(TimeCastHandler.strCastdateTime(user.getLastdate()));
        }

        userService.add(user);
        return new Result(true, StatusCode.OK,"添加成功");
    }

    /**
     * 查询全部用户
     * @return   返回结果集
     */
    @GetMapping()
    public Result queryAll(){
        return new Result(true,StatusCode.OK,"查询成功",userService.queryAll());
    }

    /**
     * 用户登陆查询
     * @param login  登陆人手机号和密码
     * @return       返回状态信息
     */
    @PostMapping("/login")
    public Result queryLogin(@RequestBody Login login){
        User user = userService.queryByLogin(login);
        if (user != null){
            return new Result(true,StatusCode.OK,"查询成功");
        }
        return new Result(true,StatusCode.OK,"查询失败");
    }
}
