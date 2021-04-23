package com.zzq.model;

import java.io.Serializable;

/**
 * @Author Zhang zq
 * @Date 2021/4/23 17:22
 * @Description
 */
public class Login implements Serializable {

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 密码
     */
    private String password;

    @Override
    public String toString() {
        return "Login{" +
                "mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Login(String mobile, String password) {
        this.mobile = mobile;
        this.password = password;
    }

    public Login() {
    }
}
