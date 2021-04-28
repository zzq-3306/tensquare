package com.zzq.service;

/**
 * @Author Zhang zq
 * @Date 2021/4/27 17:19
 * @Description
 */
public interface FriendService {

    /**
     * 添加好友或者非好友
     * @param friendid  友好或非好友的id
     * @param type      1 喜欢      2不喜欢
     * @return      返回状态信息
     */
    int add(String friendid, String type);

    /**
     * 删除好友
     * @param friendid  好友的id
     * @return      返回状态信息
     */
    int deleteByFriendId(String friendid);
}
