package com.zzq.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzq.mapper.FriendMapper;
import com.zzq.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import util.JedisPoolUtil;

import java.io.IOException;
import java.util.Map;

/**
 * @Author Zhang zq
 * @Date 2021/4/27 17:20
 * @Description
 */
@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendMapper friendMapper;

    /**
     * 添加好友或者非好友
     * @param friendid  友好或非好友的id
     * @param type      1 喜欢      2不喜欢
     * @return      返回状态信息
     */
    @Override
    public int add(String friendid, String type) {
        String id = "";
        Jedis jedis = JedisPoolUtil.getJedis();
        String loginUser = jedis.get("loginUser");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map map = objectMapper.readValue(loginUser, Map.class);
             id = (String) map.get("id");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return friendMapper.add(id,friendid,type);
    }

    /**
     * 删除好友
     * @param friendid  好友的id
     * @return      返回状态信息
     */
    @Override
    public int deleteByFriendId(String friendid) {
        return friendMapper.deleteByFriendId(friendid);
    }
}
