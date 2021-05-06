package com.zzq.controller;

import com.zzq.model.Admin;
import com.zzq.model.AdminLogin;
import com.zzq.service.AdminService;
import model.PageResult;
import model.Result;
import model.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import util.JwtUtil;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Zhang zq
 * @Date 2021/4/25 21:52
 * @Description
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 添加管理员
     * @param admin  管理员信息
     * @return       返回状态信息
     */
    @PostMapping()
    public Result add(@RequestBody Admin admin){
        adminService.add(admin);
        return new Result(true, StatusCode.OK,"successful operation");
    }

    /**
     * 查询全部管理员
     * @return  返回结果集list
     */
    @GetMapping()
    public Result queryAll(){
        List<Admin> list = adminService.queryAll();
        return new Result(true,StatusCode.OK,"成功查询到数据",list);
    }

    /**
     * 根据id   返回一个管理员
     * @param adminId  管理员id
     * @return    返回结果和状态
     */
    @GetMapping("/{adminId}")
    public Result queryById(@PathVariable String adminId){
        Admin admin = adminService.queryById(adminId);
        return new Result(true,StatusCode.OK,"操作成功",admin);
    }

    /**
     * 修改管理员信息
     * @param adminId  管理员id
     * @param admin    管理员信息
     * @return       返回状态信息
     */
    @PutMapping("/{adminId}")
    public Result update(@PathVariable String adminId,@RequestBody Admin admin){
        admin.setId(adminId);
        adminService.update(admin);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /**
     * 根据id删除管理员
     * @param adminId   管理员id
     * @return      返回状态信息  是否成功
     */
    @DeleteMapping("/{adminId}")
    public Result  delete(@PathVariable String adminId){
        adminService.deleteById(adminId);
        return new Result(true,StatusCode.OK,"操作成功");
    }

    /**
     * 条件查询 带分页
     * @param searchMap  查询条件
     * @param page     当前页
     * @param size     页面大小
     * @return      返回结果集
     */
    @PostMapping("/search/{page}/{size}")
    public Result queryLikeAndPage(@RequestBody Map searchMap,@PathVariable int page ,@PathVariable int size){
        Page<Admin> adminPage = adminService.queryLikeAndPage(searchMap,page,size);
        return new Result(true,StatusCode.OK,"查询成功",
                new PageResult<Admin>(adminPage.getTotalElements(),adminPage.getContent()));
    }

    /**
     * 管理员登陆
     * @param login  管理员登陆的信息
     * @return     返回状态信息
     */
    @PostMapping("/login")
    public Result login(@RequestBody AdminLogin login){
        Admin admin = adminService.queryByNameAndPassword(login);
        if (admin != null){
            String token = jwtUtil.createJWT(admin.getId(), admin.getLoginname(), "admin");
            Map hashMap = new HashMap<>();
            hashMap.put("token",token);
            hashMap.put("name",admin.getLoginname());
            redisTemplate.opsForValue().set("loginAdmin",admin);
            return new Result(true,StatusCode.OK,"successful operation",hashMap);
        }
        return new Result(false,StatusCode.ERROR,"fail operation");
    }
}
