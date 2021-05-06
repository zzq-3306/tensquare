package com.zzq.model;

import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @Author Zhang zq
 * @Date 2021/4/28 20:46
 * @Description
 */
@Document(indexName = "tensquare",type = "column")
public class Column implements Serializable {

    @Id
    private String id;

    private String name;

    private String summary;
    private String userid;
    private String createtime;
    private String checktime;
    private String state;

    @Override
    public String toString() {
        return "Column{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", summary='" + summary + '\'' +
                ", userid='" + userid + '\'' +
                ", createtime='" + createtime + '\'' +
                ", checktime='" + checktime + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getChecktime() {
        return checktime;
    }

    public void setChecktime(String checktime) {
        this.checktime = checktime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Column(String id, String name, String summary, String userid, String createtime, String checktime, String state) {
        this.id = id;
        this.name = name;
        this.summary = summary;
        this.userid = userid;
        this.createtime = createtime;
        this.checktime = checktime;
        this.state = state;
    }

    public Column() {
    }
}
