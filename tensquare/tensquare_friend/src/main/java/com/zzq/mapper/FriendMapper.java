package com.zzq.mapper;

import com.zzq.model.Friend;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


/**
 * @Author Zhang zq
 * @Date 2021/4/27 17:02
 * @Description
 */
public interface FriendMapper  extends JpaRepository<Friend,String> {

    /**
     * 根据用户id和被关注用户的id查询记录个数
     * @param userid   用户id
     * @param friendid  被关注的id
     * @return          返回记录数
     */
    @Query("select count(f) from Friend f where f.userid = ?1 and f.friendid = ?2")
    int selectCount(String userid,String friendid);


    /**
     * 更新为互相喜欢
     * @param userid
     * @param friendid
     * @param islike
     */
    @Modifying
    @Query("update Friend f set f.islike=?3 where f.userid=?1 and f.friendid=?2")
    void updateLike(String userid ,String friendid ,String islike);


}
