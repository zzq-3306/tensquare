package com.zzq.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author Zhang zq
 * @Date 2021/4/28 20:45
 * @Description
 */
@Entity
@Table(name = "tb_channel")
public class Channel implements Serializable {

    @Id
    private String Id;

    private String name;

    private String state;

    @Override
    public String toString() {
        return "Channel{" +
                "Id='" + Id + '\'' +
                ", name='" + name + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    public Channel(String id, String name, String state) {
        Id = id;
        this.name = name;
        this.state = state;
    }

    public String getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public Channel() {
    }
}
