package com.zzq.service.impl;

import com.zzq.model.Label;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author Zhang zq
 * @Date 2021/4/24 11:55
 * @Description
 */
public interface LabelService {

    /**
     * 查询全部的表标签
     * @return  返回所有的标签list
     */
     List<Label> queryAll();


    /**
     * 根据id查询标签
     * @param id   标签id
     * @return   返回标签id对应的标签信息
     */
     Label queryById(String id);


    /**
     * 添加标签
     * @param label   标签信息
     */
     void add(Label label);


    /**
     * 修改标签
     * @param label 标签信息
     */
     void modify(Label label);


    /**
     * 根据标签id删除标签
     * @param id  标签id
     */
     void delete(String id);




    /**
     * 条件查询
     * @param searchMap
     * @return
     */
     List<Label> findSearch(Map searchMap);


    /**
     * 带分页的条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
     Page<Label> findSearch(Map searchMap, int page, int size);

}
