package com.zzq.mapper;

import com.sun.deploy.uitoolkit.impl.fx.FXPluginToolkit;
import com.zzq.model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Author Zhang zq
 * @Date 2021/4/25 15:59
 * @Description
 */
public interface FollowMapper extends JpaRepository<Follow,String>, JpaSpecificationExecutor<Follow> {

    /**
     * 关注用户添加tb_follow表
     * @param follow
     * @return
     */
    @Transactional()
    @Modifying
    @Query(value = "insert into tb_follow values(:#{#follow.userid},:#{#follow.targetuser})",nativeQuery = true)
    int addFollow(Follow follow);


    /**
     * 删除tb_follow信息  取消关注
     * @param userid       我的id
     * @param targetuser   删除某用户关注   的id
     * @return    返回状态信息
     */
    @Transactional
    @Modifying
    @Query(value = "delete from tb_follow where userid = :#{#follow.userid} and targetuser = :#{#follow.targetuser}",nativeQuery = true)
    int deleteFollow(Follow follow);

    /**
     * 查询我的关注列表ID
     * @param id   我的id
     * @return      返回列表id集合   List
     */
    @Query(value = "select targetuser from tb_follow where userid = ?",nativeQuery = true)
    List<String> queryMyFollowId(String id);
}
