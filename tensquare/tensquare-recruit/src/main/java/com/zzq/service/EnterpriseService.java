package com.zzq.service;

import com.zzq.model.Enterprise;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * @Author Zhang zq
 * @Date 2021/4/28 16:56
 * @Description
 */
public interface EnterpriseService {

    /**
     * 增加企业
     * @param enterprise 企业信息
     */
    void add(Enterprise enterprise);

    /**
     * 企业全部列表
     * @return  返回结果集
     */
    List<Enterprise> queryAll();

    Enterprise queryById(String enterpriseId);

    /**
     * 修改企业
     * @param enterpriseId  企业的id
     */
    void updateById(String enterpriseId,Enterprise enterprise);

    /**
     * 根据id删除企业
     * @param enterpriseId  企业的id
     */
    void deleteById(String enterpriseId);

    /**
     * 根据条件查询企业列表
     * @param enterprise  搜索条件
     * @return        返回结果集
     */
    List<Enterprise> search(Enterprise enterprise);

    /**
     * 热门企业列表
     * @return   返回结果集
     */
    List<Enterprise> hotList();

    /**
     * enterprise 分页模糊查询
     * @param enterprise  搜索条件
     * @param page        当前页
     * @param size        页面大小
     * @return            返回结果集
     */
    Page<Enterprise> pageAndLike(Enterprise enterprise, int page, int size);
}
