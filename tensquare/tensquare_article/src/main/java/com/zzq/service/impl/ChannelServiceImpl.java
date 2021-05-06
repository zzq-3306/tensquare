package com.zzq.service.impl;

import com.zzq.mapper.ArticleMapper;
import com.zzq.mapper.ChannelMapper;
import com.zzq.model.Channel;
import com.zzq.service.ArticleService;
import com.zzq.service.ChannelService;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Zhang zq
 * @Date 2021/4/28 21:03
 * @Description
 */
@Service
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    private ChannelMapper channelMapper;

    /**
     * 添加频道
     * @param channel  频道信息
     */
    @Override
    public void add(Channel channel) {
        channelMapper.save(channel);
    }

    /**
     * 查询全部
     * @return  返回结果集
     */
    @Override
    public List<Channel> queryAll() {
        Iterable<Channel> all = channelMapper.findAll();
        ArrayList<Channel> channels = Lists.newArrayList();
        all.forEach(channels::add);
        return channels;
    }

    /**
     * 根据id查询频道信息
     * @param channelId  频道的id
     * @return           返回id对应的信息
     */
    @Override
    public Channel queryById(String channelId) {
        return channelMapper.findById(channelId).get();
    }

    /**
     * 修改频道信息
     * @param channelId  频道id
     * @param channel    频道信息
     */
    @Override
    public void updateById(String channelId, Channel channel) {
        channel.setId(channelId);
        channelMapper.save(channel);
    }

    /**
     * 根据频道id删除
     * @param channelId   频道id
     */
    @Override
    public void deleteById(String channelId) {
        channelMapper.deleteById(channelId);
    }
}
