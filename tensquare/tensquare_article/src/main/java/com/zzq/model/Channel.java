package com.zzq.model;

import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @Author Zhang zq
 * @Date 2021/4/28 20:45
 * @Description
 */
@Document(indexName = "tensquare",type = "channel")
public class Channel implements Serializable {

    @Id
    private String id;

    private String name;

    private String state;

    @Override
    public String toString() {
        return "Channel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    public Channel(String id, String name, String state) {
        this.id = id;
        this.name = name;
        this.state = state;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Channel() {
    }
}
