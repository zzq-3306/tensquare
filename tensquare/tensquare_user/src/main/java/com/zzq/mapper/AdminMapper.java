package com.zzq.mapper;

import com.zzq.model.Admin;
import com.zzq.model.AdminLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @Author Zhang zq
 * @Date 2021/4/25 21:54
 * @Description
 */
public interface AdminMapper extends JpaRepository<Admin,String>, JpaSpecificationExecutor<Admin> {

    /**
     * 管理员登陆
     * @param loginname  管理员登陆的名字
     * @return     返回状态信息
     */
    Admin queryByLoginname(String loginname);
}
