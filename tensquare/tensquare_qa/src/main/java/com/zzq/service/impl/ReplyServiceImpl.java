package com.zzq.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzq.mapper.ReplyMapper;
import com.zzq.model.Reply;
import com.zzq.service.ProblemService;
import com.zzq.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import util.JedisPoolUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author Zhang zq
 * @Date 2021/4/26 16:21
 * @Description
 */
@Service
//@FeignClient(name = "tensquare-user")
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyMapper replyMapper;

    @Autowired
    private ProblemService problemService;

    /**
     * 增加回答
     * @param reply  回答信息
     */
    @Override
    public void add(Reply reply) {
        replyMapper.save(reply);
    }


    /**
     * 回答全部列表
     * @return  返回结果集
     */
    @Override
    public List<Reply> queryAll() {
        return replyMapper.findAll();
    }

    /**
     * 根据id查询回答信息
     * @param replyId   回答id
     * @return      返回结果
     */
    @Override
    public Reply queryById(String replyId) {
        return replyMapper.getOne(replyId);
    }

    /**
     * 修改reply
     * @param reply   回答详情
     */
    @Override
    public void update(Reply reply) {
        replyMapper.save(reply);
    }

    /**
     * 根据id删除
     * @param replyId  回答信息id
     */
    @Override
    public void delete(String replyId) {
        replyMapper.deleteById(replyId);
    }

    /**
     * 回答分页
     * @param searchMap  搜索条件
     * @param page       当前页
     * @param size       页面大小
     * @return        返回结果集
     */
    @Override
    public Page<Reply> searchPageAndLike(Map searchMap, int page, int size) {
        Specification<Reply> specification = createSpecification(searchMap);
        PageRequest of = PageRequest.of(page, size);
        return replyMapper.findAll(specification,of);
    }

    /**
     * 根据问题id查询回答列表
     * @param problemId  问题id
     * @return   返回结果集
     */
    @Override
    public List<Reply> queryByProblemId(String problemId) {
        return replyMapper.queryProblemId(problemId);
    }

    /**
     * 回答问题
     * @param reply  回答信息详情
     */
    @Override
    @Transactional
    public void saveReply(Reply reply) {
        problemService.updateByProblemId(reply.getId());
        replyMapper.save(reply);
    }


    /**
     * 我的回答列表
     * @param page  当前页
     * @param size   页面大小
     * @return      返回结果信息
     */
    @Override
    public Page<Reply> myList(int page, int size) {
        Page<Reply> list = null;
        Jedis jedis = JedisPoolUtil.getJedis();
        String loginUser = jedis.get("loginUser");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map map = objectMapper.readValue(loginUser, Map.class);
            String id = (String) map.get("id");
            System.out.println("redis中存放的登陆人信息id = " + id);
            PageRequest of = PageRequest.of(page, size);
            list = replyMapper.queryByUserId(id, of);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * jpa的复杂查询判断
     * @param searchMap
     * @return
     */
    private Specification<Reply> createSpecification(Map searchMap){
        return new Specification<Reply>() {
            @Override
            public Predicate toPredicate(Root<Reply> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                ArrayList<Predicate> list = new ArrayList<>();

                if(searchMap.get("id")!= null && !"".equals(searchMap.get("id"))){
                    list.add(criteriaBuilder.equal(root.get("id").as(String.class),(String)searchMap.get("id")));
                }
                if(searchMap.get("problemid")!= null && !"".equals(searchMap.get("problemid"))){
                    list.add(criteriaBuilder.equal(root.get("problemid").as(String.class),(String)searchMap.get("problemid")));
                }
                if(searchMap.get("content")!= null && !"".equals(searchMap.get("content"))){
                    list.add(criteriaBuilder.like(root.get("content").as(String.class),(String)searchMap.get("content")));
                }
                if(searchMap.get("createtime")!= null && !"".equals(searchMap.get("createtime"))){
                    list.add(criteriaBuilder.like(root.get("createtime").as(String.class),(String) searchMap.get("createtime")));
                }
                if(searchMap.get("updatetime")!= null && !"".equals(searchMap.get("updatetime"))){
                    list.add(criteriaBuilder.like(root.get("updatetime").as(String.class),(String)searchMap.get("updatetime")));
                }
                if(searchMap.get("userid")!= null && !"".equals(searchMap.get("userid"))){
                    list.add(criteriaBuilder.equal(root.get("userid").as(String.class),(String) searchMap.get("userid")));
                }
                if(searchMap.get("nickname")!= null && !"".equals(searchMap.get("nickname"))){
                    list.add(criteriaBuilder.like(root.get("nickname").as(String.class),(String)searchMap.get("nickname")));
                }
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
    }
}
