package com.zzq.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author Zhang zq
 * @Date 2021/4/26 16:17
 * @Description
 */
@Entity
@Table(name = "tb_reply")
public class Reply implements Serializable {

    @Id
    private String id;

    private String problemid;

    private String content;
    private String createtime;
    private String updatetime;
    private String userid;
    private String nickname;

    @Override
    public String toString() {
        return "Reply{" +
                "id='" + id + '\'' +
                ", problemid='" + problemid + '\'' +
                ", content='" + content + '\'' +
                ", createtime='" + createtime + '\'' +
                ", updatetime='" + updatetime + '\'' +
                ", userid='" + userid + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProblemid() {
        return problemid;
    }

    public void setProblemid(String problemid) {
        this.problemid = problemid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Reply(String id, String problemid, String content, String createtime, String updatetime, String userid, String nickname) {
        this.id = id;
        this.problemid = problemid;
        this.content = content;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.userid = userid;
        this.nickname = nickname;
    }

    public Reply() {
    }
}
