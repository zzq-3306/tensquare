package com.zzq.model;

import com.sun.javafx.beans.IDProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author Zhang zq
 * @Date 2021/4/28 16:48
 * @Description   招聘实体类
 */
@Entity
@Table(name = "tb_recruit")
public class Recruit implements Serializable {

    /**
     * id
     */
    @Id
    private String id;

    /**
     * 职位名称
     */
    private String jobname;

    /**
     * 薪资范围
     */
    private String salary;

    /**
     * 经验要求
     */
    private String conditions;

    /**
     * 学历要求
     */
    private String education;

    /**
     * 任职方式
     */
    private String type;

    /**
     * 办公地址
     */
    private String address;

    /**
     * 企业地址
     */
    private String eid;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String createtime;

    /**
     * 状态
     */
    private String state;

    /**
     * 网址
     */
    private String url;

    /**
     * 标签
     */
    private String label;

    /**
     * 职位描述
     */
    private String content1;

    /**
     * 职位要求
     */
    private String content2;

    @Override
    public String toString() {
        return "Recruit{" +
                "id='" + id + '\'' +
                ", jobname='" + jobname + '\'' +
                ", salary='" + salary + '\'' +
                ", conditions='" + conditions + '\'' +
                ", education='" + education + '\'' +
                ", type='" + type + '\'' +
                ", address='" + address + '\'' +
                ", eid='" + eid + '\'' +
                ", createtime='" + createtime + '\'' +
                ", state='" + state + '\'' +
                ", url='" + url + '\'' +
                ", label='" + label + '\'' +
                ", content1='" + content1 + '\'' +
                ", content2='" + content2 + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobname() {
        return jobname;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getconditions() {
        return conditions;
    }

    public void setconditions(String conditions) {
        this.conditions = conditions;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getContent1() {
        return content1;
    }

    public void setContent1(String content1) {
        this.content1 = content1;
    }

    public String getContent2() {
        return content2;
    }

    public void setContent2(String content2) {
        this.content2 = content2;
    }

    public Recruit(String id, String jobname, String salary, String conditions, String education, String type, String address, String eid, String createtime, String state, String url, String label, String content1, String content2) {
        this.id = id;
        this.jobname = jobname;
        this.salary = salary;
        this.conditions = conditions;
        this.education = education;
        this.type = type;
        this.address = address;
        this.eid = eid;
        this.createtime = createtime;
        this.state = state;
        this.url = url;
        this.label = label;
        this.content1 = content1;
        this.content2 = content2;
    }

    public Recruit() {
    }
}
