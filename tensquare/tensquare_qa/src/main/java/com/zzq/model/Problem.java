package com.zzq.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author Zhang zq
 * @Date 2021/4/26 11:57
 * @Description
 */
@Entity
@Table(name = "tb_problem")
public class Problem implements Serializable {

    @Id
    private String id;

    private String title;

    private String content;

    private String createtime;

    private String updatetime;

    private String userid;
    private String nickname;
    private String visits;
    private String thumbup;
    private String reply;
    private String solve;
    private String replyname;
    private String replytime;

    @Override
    public String toString() {
        return "Problem{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createtime='" + createtime + '\'' +
                ", updatetime='" + updatetime + '\'' +
                ", userid='" + userid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", visits='" + visits + '\'' +
                ", thumbup='" + thumbup + '\'' +
                ", reply='" + reply + '\'' +
                ", solve='" + solve + '\'' +
                ", replyname='" + replyname + '\'' +
                ", replytime='" + replytime + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getVisits() {
        return visits;
    }

    public void setVisits(String visits) {
        this.visits = visits;
    }

    public String getThumbup() {
        return thumbup;
    }

    public void setThumbup(String thumbup) {
        this.thumbup = thumbup;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getSolve() {
        return solve;
    }

    public void setSolve(String solve) {
        this.solve = solve;
    }

    public String getReplyname() {
        return replyname;
    }

    public void setReplyname(String replyname) {
        this.replyname = replyname;
    }

    public String getReplytime() {
        return replytime;
    }

    public void setReplytime(String replytime) {
        this.replytime = replytime;
    }

    public Problem(String id, String title, String content, String createtime, String updatetime, String userid, String nickname, String visits, String thumbup, String reply, String solve, String replyname, String replytime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.userid = userid;
        this.nickname = nickname;
        this.visits = visits;
        this.thumbup = thumbup;
        this.reply = reply;
        this.solve = solve;
        this.replyname = replyname;
        this.replytime = replytime;
    }

    public Problem() {
    }

}
