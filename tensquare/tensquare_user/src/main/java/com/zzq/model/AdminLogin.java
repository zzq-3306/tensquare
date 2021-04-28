package com.zzq.model;

import java.io.Serializable;

/**
 * @Author Zhang zq
 * @Date 2021/4/25 22:26
 * @Description
 */
public class AdminLogin implements Serializable {

    /**
     * 登陆名
     */
    private String loginname;

    /**
     * 密码
     */
    private String password;

    @Override
    public String toString() {
        return "AdminLogin{" +
                "loginname='" + loginname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AdminLogin(String loginname, String password) {
        this.loginname = loginname;
        this.password = password;
    }

    public AdminLogin() {
    }
}
