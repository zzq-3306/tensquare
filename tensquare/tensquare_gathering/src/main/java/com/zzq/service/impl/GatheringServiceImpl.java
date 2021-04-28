package com.zzq.service.impl;

import com.zzq.mapper.GatheringMapper;
import com.zzq.model.Gathering;
import com.zzq.service.GatheringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Zhang zq
 * @Date 2021/4/28 9:46
 * @Description
 */
@Service
public class GatheringServiceImpl implements GatheringService {

    @Autowired
    private GatheringMapper gatheringMapper;
    @Autowired
    private IdWorker idWorker;

    /**
     * 增加活动
     * @param gathering  活动详情信息
     */
    @Override
    public void add(Gathering gathering) {
        gathering.setId(idWorker.nextId()+"");
        gatheringMapper.save(gathering);
    }

    /**
     * 活动全部列表
     * @return    返回结果集list
     */
    @Override
    public List<Gathering> queryAll() {
        return gatheringMapper.findAll();
    }

    /**
     * 根据id查询活动
     * @param gatheringId   活动id
     * @return               返回活动信息
     */
    @Override
    @Cacheable(value = "gatherings",key = "#gatheringId")
    public Gathering queryById(String gatheringId) {
        System.out.println("--------------------------------");
        return gatheringMapper.findById(gatheringId).get();
    }

    /**
     * 修改活动
     * @param gatheringId   活动id
     */
    @Override
    @CacheEvict(value = "gatherings",key = "#gatheringId")
    public void updateById(String gatheringId, Gathering gathering) {
        gathering.setId(gatheringId);
        gatheringMapper.save(gathering);
    }

    /**
     * 根据活动id查询活动
     * @param gatheringId  活动id
     */
    @Override
    @CacheEvict(value = "gatherings",key = "#gatheringId")
    public void deleteById(String gatheringId) {
        gatheringMapper.deleteById(gatheringId);
    }

    /**
     * 活动实体类
     * @param searchMap  搜索条件
     * @return           返回结果集
     */
    @Override
    public List<Gathering> search(Map searchMap) {
        Specification<Gathering> specification = createSpecification(searchMap);
        return gatheringMapper.findAll(specification);
    }
    /**
     * 活动分页
     * @param searchMap   搜素条件
     * @param page       当前页
     * @param size         页面大小
     * @return          返回结果集
     */
    @Override
    public Page<Gathering> pageAndLike(Map searchMap, int page, int size) {
        Specification<Gathering> specification = createSpecification(searchMap);
        PageRequest of = PageRequest.of(page, size);
        return gatheringMapper.findAll(specification,of);
    }

    /**
     * 根据城市显示分页内容
     * @param city       城市
     * @param page       当前页
     * @param size       页面大小
     * @return           返回结果集
     */
    @Override
    public Page<Gathering> cityPageAndLike(String city, int page, int size) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("city",city);
        Specification<Gathering> specification = createSpecification(map);
        PageRequest of = PageRequest.of(page, size);
        return gatheringMapper.findAll(specification,of);
    }

    /**
     * 封装jpa复杂查询
     * @param searchMap
     * @return
     */
    private Specification<Gathering> createSpecification(Map searchMap){
        return new Specification<Gathering>() {
            @Override
            public Predicate toPredicate(Root<Gathering> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                ArrayList<Predicate> list = new ArrayList<>();

                if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))){
                    list.add(criteriaBuilder.equal(root.get("id").as(String.class),searchMap.get("id")));
                }

                if (searchMap.get("name")!=null && !"".equals(searchMap.get("name"))){
                    list.add(criteriaBuilder.like(root.get("name").as(String.class),(String) searchMap.get("name")));
                }

                if (searchMap.get("summary")!=null && !"".equals(searchMap.get("summary"))){
                    list.add(criteriaBuilder.like(root.get("summary").as(String.class),(String) searchMap.get("summary")));
                }

                if (searchMap.get("detail")!=null && !"".equals(searchMap.get("detail"))){
                    list.add(criteriaBuilder.like(root.get("detail").as(String.class),(String) searchMap.get("detail")));
                }

                if (searchMap.get("sponsor")!=null && !"".equals(searchMap.get("sponsor"))){
                    list.add(criteriaBuilder.like(root.get("sponsor").as(String.class),(String) searchMap.get("sponsor")));
                }

                if (searchMap.get("starttime")!=null && !"".equals(searchMap.get("starttime"))){
                    list.add(criteriaBuilder.like(root.get("starttime").as(String.class),(String) searchMap.get("starttime")));
                }

                if (searchMap.get("endtime")!=null && !"".equals(searchMap.get("endtime"))){
                    list.add(criteriaBuilder.like(root.get("endtime").as(String.class),(String) searchMap.get("endtime")));
                }

                if (searchMap.get("address")!=null && !"".equals(searchMap.get("address"))){
                    list.add(criteriaBuilder.like(root.get("address").as(String.class),(String) searchMap.get("address")));
                }

                if (searchMap.get("enrolltime")!=null && !"".equals(searchMap.get("enrolltime"))){
                    list.add(criteriaBuilder.like(root.get("enrolltime").as(String.class),(String) searchMap.get("enrolltime")));
                }

                if (searchMap.get("state")!=null && !"".equals(searchMap.get("state"))){
                    list.add(criteriaBuilder.equal(root.get("state").as(String.class),searchMap.get("state")));
                }

                if (searchMap.get("city")!=null && !"".equals(searchMap.get("city"))){
                    list.add(criteriaBuilder.like(root.get("city").as(String.class),(String) searchMap.get("city")));
                }
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
    }
}
