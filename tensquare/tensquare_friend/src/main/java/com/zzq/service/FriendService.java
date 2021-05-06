package com.zzq.service;

/**
 * @Author Zhang zq
 * @Date 2021/4/27 17:19
 * @Description
 */
public interface FriendService {

    /**
     * 添加好友
     * @param userid      用户id
     * @param friendid     被关注的用户id
     * @return
     */
    int addFriend(String userid,String friendid);
}
