package com.zzq.mapper;

import com.zzq.model.Follow;
import com.zzq.model.Login;
import com.zzq.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Author Zhang zq
 * @Date 2021/4/23 14:51
 * @Description
 */
public interface UserMapper extends JpaRepository<User,String>, JpaSpecificationExecutor<User> {


    /**
     * 查询登陆用户
     * @param mobile   登陆用户信息
     * @return    返回登陆用户信息
     */
    User queryByMobile(String mobile);

    /**
     * 根据用户id修改follow   关注用户
     * @param userId  用户id
     * @return    返回修改状态
     */
    @Modifying
    @Query(value = "update tb_user set followcount = followcount + 1,fanscount = fanscount +1 where id = ?1 ",nativeQuery = true)
    int updateFollowById(String userId);

    /**
     * 删除用户关注
     * @param userId   用户的id
     * @return      返回状态信息
     */
    @Modifying
    @Query(value = "update tb_user set followcount = followcount - 1 ,fanscount = fanscount -1 where id = ?1 ",nativeQuery = true)
    int deleteFollowById(String userId);


    /**
     * 查询我的粉丝
     * @param id  我的id
     * @return  返回结果list
     */
    @Query(value = "select u.* from tb_user u inner join tb_follow f on u.id = f.userid and f.targetuser = ? ",nativeQuery = true)
    List<User> queryFansById(String id);


    /**
     * 根据我的id   查询我的关注
     * @param id   我的id
     * @return     返回结果集
     */
    @Query(value = "select * from tb_user u where exists ( select targetuser from tb_follow where userid = ? and targetuser=u.id )",nativeQuery = true)
    List<User> queryFollowById(String id);


}
