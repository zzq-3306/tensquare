package com.zzq.service;

import com.zzq.model.Recruit;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author Zhang zq
 * @Date 2021/4/28 16:43
 * @Description
 */
public interface RecruitService {

    /**
     * 增加招聘
     * @param recruit    招聘信息
     */
    void add(Recruit recruit);

    /**
     * 招聘全部列表
     */
    List<Recruit> queryAll();

    /**
     * 根据id查询招聘
     * @param recruitId  招聘id
     * @return         返回结果
     */
    Recruit queryById(String recruitId);

    /**
     * 修改招聘
     * @param recruit   招聘信息
     * @param recruitId   招聘id
     */
    void updateById(Recruit recruit, String recruitId);

    /**
     * 根据id删除招聘信息
     * @param recruitId  招聘id
     */
    void deleteById(String recruitId);

    /**
     * 根据条件查询招聘列表
     * @param recruit   条件
     * @return         返回结果list
     */
    List<Recruit> search(Recruit recruit);

    /**
     * 招聘分页
     * @param recruit  搜索条件
     * @param page     当前页
     * @param size     页面大小
     * @return         返回结果list
     */
    Page<Recruit> pageAndLike(Recruit recruit, int page, int size);

    /**
     * 推荐职位
     * @return   返回结果list
     */
    List<Recruit> recommend();

    /**
     * 最新职位
     * @return  返回结果list
     */
    List<Recruit> newList();
}
