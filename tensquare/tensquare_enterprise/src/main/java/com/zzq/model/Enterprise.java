package com.zzq.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author Zhang zq
 * @Date 2021/4/23 16:07
 * @Description  企业实体类
 */
@Entity
@Table(name = "tb_crecuit")
public class Enterprise implements Serializable {

    /**
     * 企业id
     */
    @Id
    private String id;

    /**
     * 企业名称
     */
    private String name;

    /**
     * 企业简介
     */
    private String summary;

    /**
     * 企业地址
     */
    private String address;

    /**
     * 标签列表
     */
    private String labels;

    /**
     * 坐标
     */
    private String coordinate;

    /**
     * 是否热门
     *      0非热门
     *      1热门
     */
    private String ishot;

    /**
     * LOGO
     */
    private String Logo;

    /**
     * 职位数
     */
    private String jobcount;

    /**
     * URL
     */
    private String url;

    @Override
    public String toString() {
        return "Enterprise{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", summary='" + summary + '\'' +
                ", address='" + address + '\'' +
                ", labels='" + labels + '\'' +
                ", coordinate='" + coordinate + '\'' +
                ", ishot='" + ishot + '\'' +
                ", Logo='" + Logo + '\'' +
                ", jobcount='" + jobcount + '\'' +
                ", url='" + url + '\'' +
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getIshot() {
        return ishot;
    }

    public void setIshot(String ishot) {
        this.ishot = ishot;
    }

    public String getLogo() {
        return Logo;
    }

    public void setLogo(String logo) {
        Logo = logo;
    }

    public String getJobcount() {
        return jobcount;
    }

    public void setJobcount(String jobcount) {
        this.jobcount = jobcount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Enterprise(String id, String name, String summary, String address, String labels, String coordinate, String ishot, String logo, String jobcount, String url) {
        this.id = id;
        this.name = name;
        this.summary = summary;
        this.address = address;
        this.labels = labels;
        this.coordinate = coordinate;
        this.ishot = ishot;
        Logo = logo;
        this.jobcount = jobcount;
        this.url = url;
    }

    public Enterprise() {
    }
}
