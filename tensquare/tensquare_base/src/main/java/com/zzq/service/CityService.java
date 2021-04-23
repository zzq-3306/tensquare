package com.zzq.service;

import com.zzq.mapper.CityMapper;
import com.zzq.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @Author Zhang zq
 * @Date 2021/4/23 9:52
 * @Description
 */
@Service
public class CityService {

    @Autowired
    private CityMapper cityMapper;

    /**
     * 查询全部城市信息
     * @return   返回全部信息的list
     */
    public List<City> queryAll(){
        return cityMapper.findAll();
    }

    /**
     * 根据id查询城市信息
     * @param id   城市id
     * @return   返回对应的id信息
     */
    public City queryById(String id){
        return cityMapper.findById(id).get();
    }

    /**
     * 添加城市信息
     * @param city  城市信息
     */
    public void add(City city){
        cityMapper.save(city);
    }

    /**
     * 修改城市信息
     * @param city  城市信息
     */
    public void modify(City city){
        cityMapper.save(city);
    }

    /**
     * 根据id删除城市信息
     * @param id  城市id
     */
    public void deleteById(String id){
        cityMapper.deleteById(id);
    }

    /**
     * 定义复杂条件查询方法
     * @param searchMap
     * @return
     */
    private Specification<City> createSpecification(Map searchMap){
        return new Specification<City>() {
            @Override
            public Predicate toPredicate(Root<City> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                ArrayList<Predicate> predicateList = new ArrayList<>();

                if (searchMap.get("name") != null && !"".equals(searchMap.get("name"))) {
                    predicateList.add(criteriaBuilder.like(root.get("name"),"%"+(String)searchMap.get("name")+"%"));
                }

                if (searchMap.get("ishot")!=null && !"".equals(searchMap.get("ishot"))){
                    predicateList.add(criteriaBuilder.equal(root.get("ishot").as(String.class),(String)searchMap.get("ishot")));
                }
                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }

    /**
     * 条件查询
     * @param searchMap
     * @return
     */
    public List<City> findSearch(Map searchMap){
        Specification<City> specification = createSpecification(searchMap);
        return cityMapper.findAll(specification);
    }

    /**
     * 带分页的条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public Page<City> findSearch(Map searchMap, int page , int size){
        Specification<City> specification = createSpecification(searchMap);
        PageRequest pageRequest = PageRequest.of(page, size);
        return cityMapper.findAll(specification,pageRequest);

    }
}
