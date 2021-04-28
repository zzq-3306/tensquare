package com.zzq.service;

import com.zzq.model.Gathering;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * @Author Zhang zq
 * @Date 2021/4/28 9:46
 * @Description
 */
public interface GatheringService {


    /**
     * 增加活动
     * @param gathering  活动详情信息
     */
    void add(Gathering gathering);

    /**
     * 活动全部列表
     * @return    返回结果集list
     */
    List<Gathering> queryAll();

    /**
     * 根据id查询活动
     * @param gatheringId   活动id
     * @return               返回活动信息
     */
    Gathering queryById(String gatheringId);

    /**
     * 修改活动
     * @param gatheringId   活动id
     */
    void updateById(String gatheringId, Gathering gathering);


    /**
     * 根据活动id查询活动
     * @param gatheringId  活动id
     */
    void deleteById(String gatheringId);

    /**
     * 活动实体类
     * @param searchMap  搜索条件
     * @return           返回结果集
     */
    List<Gathering> search(Map searchMap);

    /**
     * 活动分页
     * @param searchMap   搜素条件
     * @param page       当前页
     * @param size         页面大小
     * @return          返回结果集
     */
    Page<Gathering> pageAndLike(Map searchMap, int page, int size);

    /**
     * 根据城市显示分页内容
     * @param city       城市
     * @param page       当前页
     * @param size       页面大小
     * @return           返回结果集
     */
    Page<Gathering> cityPageAndLike(String city, int page, int size);
}
