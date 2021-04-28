package com.zzq.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author Zhang zq
 * @Date 2021/4/28 20:49
 * @Description
 */
@Entity
@Table(name = "tb_article")
public class Article implements Serializable {

    @Id
    private String id;

    private String columnid;
    private String userid;
    private String title;
    private String content;
    private String image;
    private String createtime;
    private String updatetime;
    private String ispublish;
    private String istop;
    private Integer visits;
    private Integer thumbup;
    private Integer comment;
    private String state;
    private String channelid;
    private String url;
    private String type;

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", columnid='" + columnid + '\'' +
                ", userid='" + userid + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", image='" + image + '\'' +
                ", createtime='" + createtime + '\'' +
                ", updatetime='" + updatetime + '\'' +
                ", ispublish='" + ispublish + '\'' +
                ", istop='" + istop + '\'' +
                ", visits=" + visits +
                ", thumbup=" + thumbup +
                ", comment=" + comment +
                ", state='" + state + '\'' +
                ", channelid='" + channelid + '\'' +
                ", url='" + url + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColumnid() {
        return columnid;
    }

    public void setColumnid(String columnid) {
        this.columnid = columnid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getIspublish() {
        return ispublish;
    }

    public void setIspublish(String ispublish) {
        this.ispublish = ispublish;
    }

    public String getIstop() {
        return istop;
    }

    public void setIstop(String istop) {
        this.istop = istop;
    }

    public Integer getVisits() {
        return visits;
    }

    public void setVisits(Integer visits) {
        this.visits = visits;
    }

    public Integer getThumbup() {
        return thumbup;
    }

    public void setThumbup(Integer thumbup) {
        this.thumbup = thumbup;
    }

    public Integer getComment() {
        return comment;
    }

    public void setComment(Integer comment) {
        this.comment = comment;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getChannelid() {
        return channelid;
    }

    public void setChannelid(String channelid) {
        this.channelid = channelid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Article(String id, String columnid, String userid, String title, String content, String image, String createtime, String updatetime, String ispublish, String istop, Integer visits, Integer thumbup, Integer comment, String state, String channelid, String url, String type) {
        this.id = id;
        this.columnid = columnid;
        this.userid = userid;
        this.title = title;
        this.content = content;
        this.image = image;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.ispublish = ispublish;
        this.istop = istop;
        this.visits = visits;
        this.thumbup = thumbup;
        this.comment = comment;
        this.state = state;
        this.channelid = channelid;
        this.url = url;
        this.type = type;
    }

    public Article() {
    }
}
