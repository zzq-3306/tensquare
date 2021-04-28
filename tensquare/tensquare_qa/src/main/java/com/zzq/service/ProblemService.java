package com.zzq.service;

import com.zzq.model.Problem;
import model.Result;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

/**
 * @Author Zhang zq
 * @Date 2021/4/26 12:02
 * @Description
 */
public interface ProblemService {

    /**
     * 添加问题
     * @param problem  问题信息
     */
    void addProblem(Problem problem);

    /**
     * 查询全部
     * @return  返回结果list
     */
    List<Problem> findAll();

    /**
     * 根据id查询问题
     * @param problemId  问题的Id
     * @return     返回信息
     */
    Problem findById(String problemId);

    /**
     * 根据id修改问题
     * @param problem      问题的信息
     */
    void updateById(Problem problem);

    /**
     * 根据id修改浏览量和回复数
     * @param id  问题id
     */
    boolean updateByProblemId(String id);

    /**
     * 根据id删除信息
     * @param problemId  问题id
     */
    void deleteById(String problemId);

    /**
     * 根据条件查询问题列表
     * @param searchMap 搜索条件
     * @return      返回问题列表
     */
    List<Problem> search(Map searchMap);

    /**
     * 问题查询和分页
     * @param searchMap  搜索条件
     * @param page      当前页
     * @param size       页面大小
     * @return     返回状态结果集
     */
    Page<Problem> queryPageAndLike(Map searchMap, int page, int size);

    /**
     * 最新问答列表
     * @param labelId     标签Id  为0则查询全部
     * @param page       当前页
     * @param size       页面大小
     * @return           返回结果
     */
    Page<Problem> findNewListByLabelId( String labelId, int page, int size);

    /**
     * 热门问答列表
     * @param label     标签Id  为0则查询全部
     * @param page       当前页
     * @param size       页面大小
     * @return           返回结果
     */
    Page<Problem> findHotListByLabelId(String label, int page, int size);

    /**
     * 等待回答列表
     * @param label  标签id
     * @param page    当前页
     * @param size     页面大小
     * @return          返回状态结果
     */
    Page<Problem> findWaitListByLabelId(String label, int page, int size);


    /**
     * Problem分页
     * @param label  标签Id
     * @param page     当前页
     * @param size    大小
     * @return       返回状态
     */
    Page<Problem> queryPage(String label, int page, int size);
}
