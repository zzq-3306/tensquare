package com.zzq.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzq.model.Follow;
import com.zzq.model.Login;
import com.zzq.model.User;
import com.zzq.service.UserService;
import io.jsonwebtoken.Claims;
import model.PageResult;
import model.Result;
import model.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private HttpServletRequest request;

    /**
     * 添加用户
     * @param user 用户信息
     * @return   状态信息
     */
    @PostMapping()
    public Result add(@RequestBody User user){

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

        userService.insert(user);
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
     * 用户登陆
     * @param login  登陆人手机号和密码
     * @return       返回状态信息
     */
    @PostMapping("/login")
    public Result queryLogin(@RequestBody Login login){

        User user = userService.queryByLogin(login);
        System.out.println("user = " + user);

        if (user != null){

            //创建用户的token
            String token = jwtUtil.createJWT(user.getId(), user.getMobile(), "user");
            Map hashMap = new HashMap<>();
            //添加token信息
            hashMap.put("token",token);
            //登陆人昵称
            hashMap.put("name",user.getNickname());
            //登陆人头像
            hashMap.put("avatar",user.getAvatar());
            System.out.println("LoginUser = " + user);
            redisTemplate.opsForValue().set("loginUser",user);
            return new Result(true,StatusCode.OK,"查询成功",hashMap);
        }
        return new Result(false,StatusCode.LOGINERROR,"用户名或者密码错误");
    }

    /**
     * 用户注册
     * @param user  用户信息
     * @param code   验证码
     * @return        返回状态信息
     */
    @PostMapping("/register/{code}")
    public Result registrUser(@RequestBody User user,@PathVariable String code){
        
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
        userService.add(user,code);
        return new Result(true,StatusCode.OK,"注册成功");
    }


    /**
     * 邮箱验证
     * @param email   邮箱
     * @return        返回状态信息
     */
    @PostMapping("/sendEmail/{email}")
    public Result sendEmail(@PathVariable String email){
        try {
            MailUtil.send(email, "验证码", userService.sendEmail(email));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(true,StatusCode.OK,"发送成功");
    }






    /**
     * 网易语音验证  发送短信
     * @param mobile  手机号
     * @return       返回状态信息
     */
    @PostMapping("/sendsms/{mobile}")
    public Result sendSMS(@PathVariable String mobile){
        try {
            String sendCode = SendCode.sendCode(mobile);
            System.out.println("sendCode = " + sendCode);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("发送短信异常....");
        }
        return new Result(true,StatusCode.OK,"发送成功");
    }


    /**
     * 根据用户id查询用户信息
     * @param userId   用户id
     * @return       返回状态信息
     */
    @GetMapping("/{userId}")
    public Result queryById(@PathVariable String userId){
        User user = userService.queryById(userId);
        if (user != null){
            return new Result(true,StatusCode.OK,"查询成功",user);
        }else{
            return new Result(false,StatusCode.ERROR,"查询失败");
        }
    }

    /**
     * 修改用户信息
     * @param userId    用户id
     * @param user       用户的信息
     * @return         返回状态信息
     */
    @PutMapping("/{userId}")
    public Result update(@PathVariable String userId,@RequestBody User user){
        user.setId(userId);
        userService.update(user);
        return new Result(true,StatusCode.OK,"更新成功");
    }

    /**
     * 根据用户id删除信息
     * @param userId    用户id
     * @return      返回状态信息
     */
    @DeleteMapping("/{userId}")
    public Result delete(@PathVariable String userId){
        Claims claims = (Claims) request.getAttribute("admin_claims");
        if (claims == null){
            return new Result(false,StatusCode.ACCESSERROR,"无权删除");
        }

        userService.delete(userId);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /**
     * 查询登陆用户的信息
     * @param session
     * @return    返回状态登陆用户信息
     */
    @GetMapping("/info")
    public Result LoginInfo(HttpSession session){
//        User loginUser = (User) session.getAttribute("loginUser");
//        String id = loginUser.getId();

        User u = null;
        Jedis jedis = JedisPoolUtil.getJedis();
        String loginUser = jedis.get("loginUser");
        //将字符串转换为对象
        ObjectMapper objectMapper = new ObjectMapper();
        try {
             u = objectMapper.readValue(loginUser, User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (u != null){
            System.out.println("redis中查到了登陆人的信息...");
            return new Result(true,StatusCode.OK,"查询成功",u);
        }

//        User user = userService.queryById(id);
//        if (user != null){
//            //再次给session赋值   刷新一下信息
//            session.setAttribute("loginUser",user);
//            return new Result(true,StatusCode.OK,"查询成功",user);
//        }
        return new Result(true,StatusCode.ERROR,"查询失败");
    }

    /**
     * 修改当前登陆用户的信息
     * @param user
     * @return
     */
    @PutMapping("/saveinfo")
    public Result updateLoginUser(@RequestBody User user,HttpSession session){
        User u = userService.update(user);
        System.out.println("u = " + u);
        if (u != null){
            session.setAttribute("loginUser",u);
            return new Result(true,StatusCode.OK,"更新成功");
        }
        return new Result(false,StatusCode.ERROR,"更新失败");
    }

    /**
     * 显示用户带条件查询并分页
     * @param searchMap    搜索条件
     * @param page         当前页
     * @param size          页面大小
     * @return           返回状态信息
     */
    @PostMapping("/search/{page}/{size}")
    public Result queryLikeAndPage(@RequestBody Map searchMap,@PathVariable int page ,@PathVariable int size){
        Page<User> userPage = userService.queryLikeAndPage(searchMap,page,size);
        if (userPage != null){
            return new Result(true,StatusCode.OK,"查询成功",
                    new PageResult<User>(userPage.getTotalElements(),userPage.getContent()));
        }else{
            return new Result(false,StatusCode.ERROR,"查询失败");
        }
    }


    /**
     * 关注某个用户
     * @param userId   被关注用户的Id
     * @return        返回状态信息
     */
    @PutMapping("/follow/{userId}")
    public Result follow(@PathVariable String userId,HttpSession session){
        User loginUser = (User) session.getAttribute("loginUser");
        //修改tb_follow表  添加关注用户和被关注用户id
        Follow follow = new Follow(loginUser.getId(), userId);
        int j = userService.addFollow(follow);
//        int j = userService.updateFollow(loginUser.getId(),userId);
        //修改被关注用户的粉丝数
        int i = userService.updateById(userId);
        if (i > 0 && j > 0){
            return new Result(true,StatusCode.OK,"OK");
        }
        return new Result(false,StatusCode.ERROR,"false");
    }

    /**
     * 删除用户关注
     * @param userId   被关注用户的id
     * @return      返回状态信息
     */
    @DeleteMapping("/follow/{userId}")
    public Result deleteFollow(@PathVariable String userId,HttpSession session){
        //将tb_user中被关注的粉丝数和关注数-1
        int i = userService.deleteFollowById(userId);
        //删除tb_follow表中的对应信息
        User loginUser = (User) session.getAttribute("loginUser");
        Follow follow = new Follow(loginUser.getId(), userId);
        int j = userService.deleteFollow(follow);

        if (i > 0 && j > 0){
            return new Result(true,StatusCode.OK,"OK");
        }
        return new Result(false,StatusCode.ERROR,"false");
    }

    /**
     * 查询我的粉丝
     * @param session
     * @return    返回状态信息
     */
    @GetMapping("/follow/myfans")
    public Result queryMyFans(HttpSession session){
        User loginUser = (User) session.getAttribute("loginUser");
        List<User> list = userService.queryFansById(loginUser.getId());
        return new Result(true,StatusCode.OK,"OK",list);
    }

    /**
     * 查询我的关注
     * @param session
     * @return   返回结果list
     */
    @GetMapping("/follow/myfollow")
    public Result myfollow(HttpSession session){
        User loginUser = (User) session.getAttribute("loginUser");
        String id = loginUser.getId();
        List<User> list = userService.queryFollowById(id);
        System.out.println("list = " + list);
        return new Result(true,StatusCode.OK,"OK",list);
    }

    /**
     * 查询我的关注ID列表
     * @param session
     * @return    返回列表id   list状态信息
     */
    @GetMapping("/follow/myfollowid")
    public Result myFollowId(HttpSession session){
        User loginUser = (User) session.getAttribute("loginUser");
        String id = loginUser.getId();
        System.out.println("id = " + id);
        List<String>  list = userService.queryMyFollowId(id);
        System.out.println("list = " + list);
        return new Result(true,StatusCode.OK,"OK",list);
    }
}
