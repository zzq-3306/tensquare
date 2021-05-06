package com.zzq.service;

import com.zzq.model.Column;

import java.util.List;

/**
 * @Author Zhang zq
 * @Date 2021/4/28 21:03
 * @Description
 */
public interface ColumnService {


    /**
     * 添加栏目
     * @param column  栏目信息
     */
    void add(Column column);

    /**
     * 查询全部
     * @return  返回结果集
     */
    List<Column> queryAll();

    /**
     * 根据id查询栏目信息
     * @param columnId  栏目的id
     * @return           返回id对应的信息
     */
    Column queryById(String columnId);

    /**
     * 修改栏目信息
     * @param columnId  栏目id
     * @param column    栏目信息
     */
    void updateById(String columnId, Column column);

    /**
     * 根据栏目id删除
     * @param columnId   栏目id
     */
    void deleteById(String columnId);
}
