package com.zzq.service.impl;

import com.zzq.mapper.SpitMapper;
import com.zzq.model.Spit;
import com.zzq.service.SpitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
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
 * @Date 2021/4/27 11:24
 * @Description
 */
@Service
public class SpitServiceImpl implements SpitService {

    @Autowired
    private SpitMapper spitMapper;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 增加吐槽
     * @param spit  吐槽的信息
     */
    @Override
    public void add(Spit spit) {
        spit.setId(idWorker.nextId()+"");
        spitMapper.save(spit);
    }

    /**
     * spit全部列表
     * @return  返回结果集
     */
    @Override
    public List<Spit> queryAll() {
        return spitMapper.findAll();
    }

    /**
     * 根据id查询吐槽
     * @return  返回结果
     */
    @Override
    public Spit queryById(String id) {
        return spitMapper.findById(id).get();
    }

    /**
     * 修改吐槽
     * @param spit  吐槽信息
     * @param spitId  吐槽id
     */
    @Override
    public void updateById(String spitId, Spit spit) {
        spit.setId(spitId);
        spitMapper.save(spit);
    }

    /**
     * 根据id删除吐槽
     * @param spitId  吐槽id
     */
    @Override
    public void deleteById(String spitId) {
        spitMapper.deleteById(spitId);
    }

    /**
     * 吐槽点赞
     * @param spitId  吐槽的Id
     */
    @Override
    public void updateThumbup(String spitId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(spitId));
        Update update = new Update();
        update.inc("thumbup",1);
        mongoTemplate.updateFirst(query,update,"spit");
    }

    /**
     * 根据条件搜索spit列表
     * @param spit    条件
     * @return      返回结果信息
     */
    @Override
    public List<Spit> search(Spit spit) {
        return searchMethod(spit);
    }
    /**
     * spit分页
     * @param spit  查询条件
     * @param page       当前页
     * @param size       页面大小
     * @return         返回结果集
     */
    @Override
    public Page<Spit> findPageAndLike(Spit spit, int page, int size) {
        return getSpit(page,size,spit);
    }

    /**
     * 根据上级id查询吐槽信息
     * @param parentid   上级吐槽id
     * @param page       当前页
     * @param size          页面大小
     * @return            返回结果集
     */
    @Override
    public Page<Spit> findByParentId(String parentid, int page, int size) {
        Spit spit = new Spit();
        spit.setParentid(parentid);
        return getSpit(page,size,spit);
    }

    /**
     * jpa复杂查询mongodb 模糊查询带分页
     * @param page
     * @param size
     * @param spit
     * @return
     */
    private Page<Spit> getSpit(Integer page, Integer size, Spit spit) {
        if (page<1){
            page=1;
        }

        Sort sort = new Sort(Sort.Direction.DESC,"id");

//        Pageable pageable = new PageRequest(page-1,size,sort);
        Pageable pageable = PageRequest.of(page-1,size);

        Query query = new Query();

        Criteria criteria = null;
        //条件id =XX
        if (spit.getId() != null && !"".equals(spit.getId())){
            criteria = Criteria.where("id").is(spit.getId());
        }
        if (spit.getContent() != null && !"".equals(spit.getContent())){
            criteria = Criteria.where("content").is(spit.getContent());
        }
        if (spit.getPublishtime() != null && !"".equals(spit.getPublishtime())){
            criteria = Criteria.where("publishtime").is(spit.getPublishtime());
        }
        if (spit.getUserid() != null && !"".equals(spit.getUserid())){
            criteria = Criteria.where("userid").is(spit.getUserid());
        }
        if (spit.getNickname() != null && !"".equals(spit.getNickname())){
            criteria = Criteria.where("nickname").is(spit.getNickname());
        }
        if (spit.getVisits() != null && !"".equals(spit.getVisits())){
            criteria = Criteria.where("visits").is(spit.getVisits());
        }
        if (spit.getThumbup() != null && !"".equals(spit.getThumbup())){
            criteria = Criteria.where("thumbup").is(spit.getThumbup());
        }
        if (spit.getShare() != null && !"".equals(spit.getShare())){
            criteria = Criteria.where("share").is(spit.getShare());
        }
        if (spit.getComment() != null && !"".equals(spit.getComment())){
            criteria = Criteria.where("comment").is(spit.getComment());
        }
        if (spit.getState() != null && !"".equals(spit.getState())){
            criteria = Criteria.where("state").is(spit.getState());
        }
        if (spit.getParentid() != null && !"".equals(spit.getParentid())){
            criteria = Criteria.where("parentid").is(spit.getParentid());
        }
        query.addCriteria(criteria);

        //mongoTemplate.count计算总数
        long total = mongoTemplate.count(query, Spit.class);

        // mongoTemplate.find 查询结果集
        List<Spit> items = mongoTemplate.find(query.with(pageable), Spit.class);
        return new PageImpl(items, pageable, total);
    }

    /**
     * 单独的条件查询
     * @param spit
     * @return
     */
    private List<Spit> searchMethod(Spit spit){
        Query query = new Query();

        Criteria criteria = null;
        //条件id =XX
        if (spit.getId() != null && !"".equals(spit.getId())){
            criteria = Criteria.where("id").is(spit.getId());
        }
        if (spit.getContent() != null && !"".equals(spit.getContent())){
            criteria = Criteria.where("content").is(spit.getContent());
        }
        if (spit.getPublishtime() != null && !"".equals(spit.getPublishtime())){
            criteria = Criteria.where("publishtime").is(spit.getPublishtime());
        }
        if (spit.getUserid() != null && !"".equals(spit.getUserid())){
            criteria = Criteria.where("userid").is(spit.getUserid());
        }
        if (spit.getNickname() != null && !"".equals(spit.getNickname())){
            criteria = Criteria.where("nickname").is(spit.getNickname());
        }
        if (spit.getVisits() != null && !"".equals(spit.getVisits())){
            criteria = Criteria.where("visits").is(spit.getVisits());
        }
        if (spit.getThumbup() != null && !"".equals(spit.getThumbup())){
            criteria = Criteria.where("thumbup").is(spit.getThumbup());
        }
        if (spit.getShare() != null && !"".equals(spit.getShare())){
            criteria = Criteria.where("share").is(spit.getShare());
        }
        if (spit.getComment() != null && !"".equals(spit.getComment())){
            criteria = Criteria.where("comment").is(spit.getComment());
        }
        if (spit.getState() != null && !"".equals(spit.getState())){
            criteria = Criteria.where("state").is(spit.getState());
        }
        if (spit.getParentid() != null && !"".equals(spit.getParentid())){
            criteria = Criteria.where("parentid").is(spit.getParentid());
        }
        query.addCriteria(criteria);

        //mongoTemplate.count计算总数
        long total = mongoTemplate.count(query, Spit.class);

        // mongoTemplate.find 查询结果集
        List<Spit> items = mongoTemplate.find(query, Spit.class);
        return items;
    }

}
