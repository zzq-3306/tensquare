package com.zzq.mapper;

import com.zzq.model.Login;
import com.zzq.model.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @Author Zhang zq
 * @Date 2021/4/23 14:51
 * @Description
 */
public interface UserMapper extends JpaRepository<User,String>, JpaSpecificationExecutor<User> {


    @Query("select * from tb_user where mobile=#{login.mobile} and password = #{login.password}")
    User queryByLogin(Login login);
}
