package com.zzq.model;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * @Author Zhang zq
 * @Date 2021/4/22 18:11
 * @Description
 */
@Entity
@Table(name = "tb_label")
public class Label implements Serializable {

    /**
     * 标签id
     */
    @Id
    private String id;

    /**
     * 标签名字
     */
    private  String labelname;

    /**
     * 标签的状态
     */
    private String state;

    /**
     * 使用的数量
     */
    private Integer count;

    /**
     * 是否推荐
     */
    private String recommend;

    /**
     * 关注数
     */
    private Integer fans;

    @Override
    public String toString() {
        return "Label{" +
                "id=" + id +
                ", labelname='" + labelname + '\'' +
                ", state='" + state + '\'' +
                ", count=" + count +
                ", recommend='" + recommend + '\'' +
                ", fans=" + fans +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabelname() {
        return labelname;
    }

    public void setLabelname(String labelname) {
        this.labelname = labelname;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public Integer getFans() {
        return fans;
    }

    public void setFans(Integer fans) {
        this.fans = fans;
    }

    public Label(String id, String labelname, String state, Integer count, String recommend, Integer fans) {
        this.id = id;
        this.labelname = labelname;
        this.state = state;
        this.count = count;
        this.recommend = recommend;
        this.fans = fans;
    }

    public Label() {
    }
}
