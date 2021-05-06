package com.zzq.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzq.mapper.FriendMapper;
import com.zzq.model.Friend;
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
     * 添加好友
     * @param userid      用户id
     * @param friendid     被关注的用户id
     * @return
     */
    @Override
    public int addFriend(String userid, String friendid) {
        //判断如果用户已经添加了好友,就不进行操作   返回0
        if(friendMapper.selectCount(userid,friendid)>0){
            return 0;
        }
        //向喜欢列表添加记录
        Friend friend = new Friend();
        friend.setUserid(userid);
        friend.setFriendid(friendid);
        friend.setIslike("0");
        friendMapper.save(friend);

        //判断对方是否喜欢你,如果喜欢  将islike设置为1
        if (friendMapper.selectCount(friendid,userid)>0){
            friendMapper.updateLike(userid,friendid,"1");
            friendMapper.updateLike(friendid,userid,"1");
        }
        return 1;
    }


}
