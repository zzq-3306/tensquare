package com.zzq.mapper;

import com.zzq.model.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Author Zhang zq
 * @Date 2021/4/28 16:55
 * @Description
 */
public interface EnterpriseMapper extends JpaRepository<Enterprise,String>, JpaSpecificationExecutor<Enterprise> {

    /**
     * 热门企业列表
     * @return   返回结果集
     */
    List<Enterprise> findByIshot(String i);
}
