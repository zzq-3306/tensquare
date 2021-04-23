package com.zzq.service;

import com.zzq.mapper.LabelMapper;
import com.zzq.model.Label;
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
 * @Date 2021/4/22 20:13
 * @Description   实现基本的增删改查
 */
@Service
public class LabelService {

    @Autowired
    private LabelMapper labelMapper;

//    @Autowired
//    private IdWorker idWorker;

    /**
     * 查询全部的表标签
     * @return  返回所有的标签list
     */
    public List<Label> queryAll(){
        return labelMapper.findAll();
    }

    /**
     * 根据id查询标签
     * @param id   标签id
     * @return   返回标签id对应的标签信息
     */
    public Label queryById(String id){
        return labelMapper.findById(id).get();
    }

    /**
     * 添加标签
     * @param label   标签信息
     */
    public void add(Label label){
        //label.setId(idWorker.nextId()+"");
        labelMapper.save(label);
    }

    /**
     * 修改标签
     * @param label 标签信息
     */
    public void modify(Label label){
        labelMapper.save(label);
    }

    /**
     * 根据标签id删除标签
     * @param id  标签id
     */
    public void delete(String id){
        labelMapper.deleteById(id);
    }

    private Specification<Label> createSpecification(Map searchMap){
        return new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                //保存查询到的值
                ArrayList<Predicate> predicateList = new ArrayList<>();
                if (searchMap.get("labelname") != null && !"".equals(searchMap.get("labelname"))){
                    predicateList.add(criteriaBuilder.like(root.get("labelname"),"%"+(String)searchMap.get("labelname")+"%" ) );
                }

                if (searchMap.get("state") != null && !"".equals(searchMap.get("state"))){
                    predicateList.add(criteriaBuilder.equal(root.get("state").as(String.class),(String)searchMap.get("state")));
                }

                if (searchMap.get("recommend")!= null && !"".equals(searchMap.get("recommend"))){
                    predicateList.add(criteriaBuilder.equal(root.get("recommend").as(String.class),(String)searchMap.get("recommend")));
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
    public List<Label> findSearch(Map searchMap){
        Specification<Label> specification = createSpecification(searchMap);
        return labelMapper.findAll(specification);
    }

    /**
     * 带分页的条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public Page<Label> findSearch(Map searchMap,int page,int size){
        Specification<Label> specification = createSpecification(searchMap);
        PageRequest pageRequest = PageRequest.of(page, size);
        return labelMapper.findAll(specification,pageRequest);
    }
}
