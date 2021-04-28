package com.zzq.model;

import com.netflix.ribbon.proxy.annotation.ClientProperties;
import com.sun.javafx.beans.IDProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author Zhang zq
 * @Date 2021/4/25 15:44
 * @Description
 */
@Entity
@Table(name = "tb_follow")
@IdClass(Primary.class)
public class Follow implements Serializable {

    @Id
    @Column(name = "userid")
    private String userid;

    @Id
    @Column(name = "targetuser")
    private String targetuser;

    @Override
    public String toString() {
        return "Follow{" +
                "userid='" + userid + '\'' +
                ", targetuser='" + targetuser + '\'' +
                '}';
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getTargetuser() {
        return targetuser;
    }

    public void setTargetuser(String targetuser) {
        this.targetuser = targetuser;
    }

    public Follow(String userid, String targetuser) {
        this.userid = userid;
        this.targetuser = targetuser;
    }

    public Follow() {
    }
}
