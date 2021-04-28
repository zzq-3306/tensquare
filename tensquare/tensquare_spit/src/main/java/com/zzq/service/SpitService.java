package com.zzq.service;

import com.zzq.model.Spit;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * @Author Zhang zq
 * @Date 2021/4/27 11:24
 * @Description
 */
public interface SpitService {

    /**
     * 增加吐槽
     * @param spit  吐槽的信息
     */
    void add(Spit spit);

    /**
     * spit全部列表
     * @return  返回结果集
     */
    List<Spit> queryAll();

    /**
     * 根据id查询吐槽
     * @return  返回结果
     */
    Spit queryById(String id);


    /**
     * 修改吐槽
     * @param spit  吐槽信息
     * @param spitId  吐槽id
     */
    void updateById(String spitId, Spit spit);

    /**
     * 根据id删除吐槽
     * @param spitId  吐槽id
     */
    void deleteById(String spitId);

    /**
     * 吐槽点赞
     * @param spitId  吐槽的Id
     */
    void updateThumbup(String spitId);

    /**
     * 根据条件搜索spit列表
     * @param spit    条件
     * @return      返回结果信息
     */
    List<Spit> search(Spit spit);

/**
 * spit分页
 * @param spit  查询条件
 * @param page       当前页
 * @param size       页面大小
 * @return         返回结果集
 */
    Page<Spit> findPageAndLike(Spit spit, int page, int size);

    /**
     * 根据上级id查询吐槽信息
     * @param parentid   上级吐槽id
     * @param page       当前页
     * @param size          页面大小
     * @return            返回结果集
     */
    Page<Spit> findByParentId(String parentid, int page, int size);
}
