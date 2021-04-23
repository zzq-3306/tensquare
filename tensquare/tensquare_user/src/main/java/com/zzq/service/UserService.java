package com.zzq.service;

import com.zzq.mapper.UserMapper;
import com.zzq.model.Login;
import com.zzq.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Zhang zq
 * @Date 2021/4/23 14:52
 * @Description
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 添加用户信息
     * @param user  用户信息
     */
    public void add(User user){
        userMapper.save(user);
    }

    /**
     * 查询全部用户信息
     * @return   返回结果集
     */
    public List<User> queryAll(){
        return userMapper.findAll();
    }

    /**
     * 用户登陆查询
     * @param login
     * @return
     */
    public User queryByLogin(Login login) {
        return userMapper.queryByLogin(login);
    }
}
