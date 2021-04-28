package com.zzq.service.impl;

import com.zzq.model.City;
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
 * @Date 2021/4/24 11:51
 * @Description
 */
public interface CityService {

    /**
     * 查询全部城市信息
     * @return   返回全部信息的list
     */
     List<City> queryAll();


    /**
     * 根据id查询城市信息
     * @param id   城市id
     * @return   返回对应的id信息
     */
     City queryById(String id);


    /**
     * 添加城市信息
     * @param city  城市信息
     */
     void add(City city);


    /**
     * 修改城市信息
     * @param city  城市信息
     */
     void modify(City city);

    /**
     * 根据id删除城市信息
     * @param id  城市id
     */
     void deleteById(String id);


    /**
     * 条件查询
     * @param searchMap
     * @return
     */
     List<City> findSearch(Map searchMap);


    /**
     * 带分页的条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
     Page<City> findSearch(Map searchMap, int page , int size);

}
