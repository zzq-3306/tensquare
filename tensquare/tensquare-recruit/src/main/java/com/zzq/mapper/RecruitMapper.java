package com.zzq.mapper;

import com.zzq.model.Enterprise;
import com.zzq.model.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Author Zhang zq
 * @Date 2021/4/28 16:42
 * @Description
 */
public interface RecruitMapper extends JpaRepository<Recruit,String>, JpaSpecificationExecutor<Recruit> {

    /**
     *    查询状态为2并以创建时间降序排序  查询前4条信息
     * @return   返回结果list
     */
    List<Recruit> findTop4ByStateOrderByCreatetimeDesc(String s);


    /**
     * 最新职位   查询状态不为0并以创建时间降序排序  查询前12条
     * @return  返回结果list
     */
    List<Recruit> findTop12ByStateNotOrderByCreatetimeDesc(String s);
}
