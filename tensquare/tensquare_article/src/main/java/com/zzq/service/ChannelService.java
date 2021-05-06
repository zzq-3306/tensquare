package com.zzq.service;

import com.zzq.model.Channel;

import java.util.List;

/**
 * @Author Zhang zq
 * @Date 2021/4/28 21:03
 * @Description
 */
public interface ChannelService {

    /**
     * 添加频道
     * @param channel  频道信息
     */
    void add(Channel channel);

    /**
     * 查询全部
     * @return  返回结果集
     */
    List<Channel> queryAll();


    /**
     * 根据id查询频道信息
     * @param channelId  频道的id
     * @return           返回id对应的信息
     */
    Channel queryById(String channelId);

    /**
     * 修改频道信息
     * @param channelId  频道id
     * @param channel    频道信息
     */
    void updateById(String channelId, Channel channel);


    /**
     * 根据频道id删除
     * @param channelId   频道id
     */
    void deleteById(String channelId);
}
