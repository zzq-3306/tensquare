package com.zzq.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;

/**
 * @Author Zhang zq
 * @Date 2021/4/27 17:02
 * @Description
 */
@Mapper
@Repository
public interface FriendMapper  {

    /**
     * 添加好友或者非好友
     * @param friendid  友好或非好友的id
     * @param type      1 喜欢      2不喜欢
     * @return      返回状态信息
     */
    @Update("insert into tb_friend values(#{userid},#{id},#{type})")
    int add(@Param("userid") String userid ,@Param("id") String friendid, @Param("type") String type);


    /**
     * 删除好友
     * @param friendid  好友的id
     * @return      返回状态信息
     */
    @Delete("delete from tb_friend where friendid = #{id}")
    int deleteByFriendId(@Param("id") String friendid);
}
