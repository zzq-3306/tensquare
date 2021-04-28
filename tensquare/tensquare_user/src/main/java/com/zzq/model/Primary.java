package com.zzq.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * @Author Zhang zq
 * @Date 2021/4/25 15:46
 * @Description
 */
public class Primary implements Serializable {

    private String userid;

    private String targetuser;

    @Override
    public String toString() {
        return "Primary{" +
                "userid='" + userid + '\'' +
                ", targetuser='" + targetuser + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Primary primary = (Primary) o;
        return Objects.equals(userid, primary.userid) &&
                Objects.equals(targetuser, primary.targetuser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userid, targetuser);
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

    public Primary(String userid, String targetuser) {
        this.userid = userid;
        this.targetuser = targetuser;
    }

    public Primary() {
    }
}
