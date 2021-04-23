package com.zzq.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author Zhang zq
 * @Date 2021/4/23 9:47
 * @Description  城市实体类
 */
@Entity
@Table(name = "tb_city")
public class City {

    /**
     * 城市的id
     */
    @Id
    private String id;

    /**
     * 城市的名称
     */
    private String name;

    /**
     * 是否热门
     */
    private String ishot;

    @Override
    public String toString() {
        return "City{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", ishot='" + ishot + '\'' +
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

    public String getIshot() {
        return ishot;
    }

    public void setIshot(String ishot) {
        this.ishot = ishot;
    }

    public City(String id, String name, String ishot) {
        this.id = id;
        this.name = name;
        this.ishot = ishot;
    }

    public City() {
    }
}
