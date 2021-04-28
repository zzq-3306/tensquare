package com.zzq.service;

import com.zzq.model.Admin;
import com.zzq.model.AdminLogin;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * @Author Zhang zq
 * @Date 2021/4/25 21:53
 * @Description
 */
public interface AdminService {

    /**
     * 添加管理员
     * @param admin  管理员信息
     */
    void add(Admin admin);

    /**
     * 查询全部管理员
     * @return  返回结果集list
     */
    List<Admin> queryAll();

    /**
     * 根据id   返回一个管理员
     * @param adminId  管理员id
     * @return    返回结果和状态
     */
    Admin queryById(String adminId);

    /**
     * 修改管理员信息
     * @param admin    管理员信息
     */
    void update(Admin admin);

    /**
     * 根据id删除管理员
     * @param id   管理员id
     */
    void deleteById(String id);

    /**
     * 条件查询 带分页
     * @param searchMap  查询条件
     * @param page     当前页
     * @param size     页面大小
     * @return      返回结果集
     */
    Page<Admin> queryLikeAndPage(Map searchMap, int page, int size);

    /**
     * 管理员登陆
     * @param login  管理员登陆的信息
     * @return     返回状态信息
     */
    Admin queryByNameAndPassword(AdminLogin login);
}
