package com.zzq.service;

import com.zzq.model.Follow;
import com.zzq.model.Login;
import com.zzq.model.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * @Author Zhang zq
 * @Date 2021/4/24 11:46
 * @Description
 */
public interface UserService {

    /**
     * 发送邮箱验证   在redis中设置过期时间   并发送到rabbitmq中
     * @param email   邮箱
     */
    String sendEmail(String email);


    /**
     * 添加用户信息
     * @param user  用户信息
     * @param code  用户输入的验证码
     */
     void add(User user,String code);

    /**
     * 查询全部用户信息
     * @return
     */
     List<User> queryAll();

    /**
     * 用户登陆查询
     * @param login
     * @return
     */
     User queryByLogin(Login login);


    /**
     * 根据用户id查询用户信息
     * @param userId   用户id
     * @return     返回状态信息
     */
     User queryById(String userId);

    /**
     * 修改用户信息
     * @param user       用户的信息
     * @return         返回状态信息
     */
     User update(User user);

    /**
     * 根据用户id删除信息
     * @param userId    用户id
     * @return      返回状态信息
     */
     void delete(String userId);

    /**
     * 显示用户带条件查询并分页
     * @param searchMap    搜索条件
     * @param page         当前页
     * @param size          页面大小
     * @return           返回状态信息
     */
    Page<User> queryLikeAndPage(Map searchMap, int page, int size);

    /**
     * 关注用户根据用户id
     * @param userId  用户id
     */
    int updateById(String userId);

    /**
     * 修改tb_follow表
     * @param userid    关注和被关注的对象
     * @param targetuser    关注和被关注的对象
     * @return      返回状态信息
     */
    int addFollow(Follow follow);
//    int updateFollow(String userid,String targetuser);

    /**
     * 删除用户关注
     * @param userId   用户的id
     * @return      返回状态信息
     */
    int deleteFollowById(String userId);


    /**
     * 删除tb_follow中的关注信息
     */
    int deleteFollow(Follow follow);

    /**
     * 查询我的粉丝
     * @param id  我的Id
     * @return  返回结果list
     */
    List<User> queryFansById(String id);

    /**
     * 查询我的关注  根据我的id
     * @param id   我的id
     * @return    返回查询到的结果集
     */
    List<User> queryFollowById(String id);

    /**
     * 查询我的关注列表ID
     * @param id   我的id
     * @return      返回列表id集合   List
     */
    List<String> queryMyFollowId(String id);

    /**
     * 添加用户
     * @param user  用户信息
     */
    void insert(User user);
}
