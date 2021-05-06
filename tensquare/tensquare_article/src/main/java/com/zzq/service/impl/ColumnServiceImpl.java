package com.zzq.service.impl;

import com.zzq.mapper.ArticleMapper;

import com.zzq.mapper.ColumnMapper;
import com.zzq.model.Column;
import com.zzq.service.ColumnService;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author Zhang zq
 * @Date 2021/4/28 21:03
 * @Description
 */
@Service
public class ColumnServiceImpl implements ColumnService {

    @Autowired
    private ColumnMapper columnMapper;

    /**
     * 添加栏目
     * @param column  栏目信息
     */
    @Override
    public void add(Column column) {
        columnMapper.save(column);
    }

    /**
     * 查询全部
     * @return  返回结果集
     */
    @Override
    public List<Column> queryAll() {
        Iterable<Column> all = columnMapper.findAll();
        ArrayList<Column> list = Lists.newArrayList();
        all.forEach(list::add);
        return list;
    }

    /**
     * 根据id查询栏目信息
     * @param columnId  栏目的id
     * @return           返回id对应的信息
     */
    @Override
    public Column queryById(String columnId) {
        return columnMapper.findById(columnId).get();
    }

    /**
     * 修改栏目信息
     * @param columnId  栏目id
     * @param column    栏目信息
     */
    @Override
    public void updateById(String columnId, Column column) {
        column.setId(columnId);
        columnMapper.save(column);
    }

    /**
     * 根据栏目id删除
     * @param columnId   栏目id
     */
    @Override
    public void deleteById(String columnId) {
        columnMapper.deleteById(columnId);
    }
}
