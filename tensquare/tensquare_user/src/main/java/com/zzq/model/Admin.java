package com.zzq.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author Zhang zq
 * @Date 2021/4/25 21:49
 * @Description
 */
@Entity
@Table(name="tb_admin")
public class Admin implements Serializable {

    /**
     * 管理员id
     */
    @Id
    private String id;

    /**
     * 登陆名称
     */
    private String loginname;

    /**
     * 密码
     */
    private String password;

    /**
     * 状态
     */
    private String state;

    @Override
    public String toString() {
        return "Admin{" +
                "id='" + id + '\'' +
                ", loginname='" + loginname + '\'' +
                ", password='" + password + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Admin(String id, String loginname, String password, String state) {
        this.id = id;
        this.loginname = loginname;
        this.password = password;
        this.state = state;
    }

    public Admin() {
    }
}
