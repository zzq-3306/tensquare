package com.zzq.service.impl;

import com.zzq.mapper.ProblemMapper;
import com.zzq.model.Problem;
import com.zzq.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author Zhang zq
 * @Date 2021/4/26 12:02
 * @Description
 */
@Service
public class ProblemServiceImpl implements ProblemService {

    @Autowired
    private ProblemMapper problemMapper;

    /**
     * 调用使用
     */
    //根据id修改浏览量和回复数
    @Transactional
    @Override
    public boolean updateByProblemId(String id){
        int i = problemMapper.updateById(id);
        System.out.println("i = " + i);
        return i > 0;
    }


    /**
     * 添加问题
     * @param problem  问题信息
     */
    @Override
    public void addProblem(Problem problem) {
        problemMapper.save(problem);
    }

    /**
     * 查询全部
     * @return  返回结果list
     */
    @Override
    public List<Problem> findAll() {
        return problemMapper.findAll();
    }

    /**
     * 根据id查询问题
     * @param problemId  问题的Id
     * @return     返回信息
     */
    @Override
    public Problem findById(String problemId) {
        return problemMapper.findById(problemId).get();
    }

    /**
     * 根据id修改问题
     * @param problem      问题的信息
     */
    @Override
    public void updateById(Problem problem) {
        problemMapper.save(problem);
    }

    /**
     * 根据id删除信息
     * @param problemId  问题id
     */
    @Override
    public void deleteById(String problemId) {
        problemMapper.deleteById(problemId);
    }

    /**
     * 根据条件查询问题列表
     * @param searchMap 搜索条件
     * @return      返回问题列表
     */
    @Override
    public List<Problem> search(Map searchMap) {
        Specification<Problem> specification = createSpecification(searchMap);
        return problemMapper.findAll(specification);
    }


    /**
     * 问题查询和分页
     * @param searchMap  搜索条件
     * @param page      当前页
     * @param size       页面大小
     * @return     返回状态结果集
     */
    @Override
    public Page<Problem> queryPageAndLike(Map searchMap, int page, int size) {
        Specification<Problem> specification = createSpecification(searchMap);
        PageRequest of = PageRequest.of(page, size);
        return problemMapper.findAll(specification,of);
    }

    /**
     * 最新问答列表
     * @param labelId     标签Id  为0则查询全部
     * @param page       当前页
     * @param size       页面大小
     * @return           返回结果
     */
    @Override
    public Page<Problem> findNewListByLabelId( String labelId, int page, int size) {
        PageRequest of = PageRequest.of(page-1, size);
        return problemMapper.findNewListByLabelId(labelId,of);
    }

    /**
     * 热门问答列表
     * @param label     标签Id  为0则查询全部
     * @param page       当前页
     * @param size       页面大小
     * @return           返回结果
     */
    @Override
    public Page<Problem> findHotListByLabelId(String label, int page, int size) {
        PageRequest of = PageRequest.of(page - 1, size);
        return problemMapper.findHotListByLabelId(label,of);
    }

    /**
     * 等待回答列表
     * @param label  标签id
     * @param page    当前页
     * @param size     页面大小
     * @return          返回状态结果
     */
    @Override
    public Page<Problem> findWaitListByLabelId(String label, int page, int size) {
        PageRequest of = PageRequest.of(page - 1, size);
        return problemMapper.findWaitListByLabelId(label,of);
    }

    /**
     * Problem分页
     * @param label  标签Id
     * @param page     当前页
     * @param size    大小
     * @return       返回状态
     */
    @Override
    public Page<Problem> queryPage(String label, int page, int size) {
        PageRequest of = PageRequest.of(page, size);
        return problemMapper.findAll(of);
    }

    /**
     * jpa的复杂查询
     * @param searchMap
     * @return
     */
    private Specification<Problem> createSpecification(Map searchMap){
        return new Specification<Problem>() {
            @Override
            public Predicate toPredicate(Root<Problem> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                ArrayList<Predicate> list = new ArrayList<>();
                if(searchMap.get("id")!= null && !"".equals(searchMap.get("id"))){
                    list.add(criteriaBuilder.equal(root.get("id").as(String.class),(String)searchMap.get("id")));
                }
                if(searchMap.get("title")!= null && !"".equals(searchMap.get("title"))){
                    list.add(criteriaBuilder.like(root.get("title").as(String.class),(String)searchMap.get("title")));
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
                    list.add(criteriaBuilder.equal(root.get("userid").as(String.class),searchMap.get("userid")));
                }
                if(searchMap.get("nickname")!= null && !"".equals(searchMap.get("nickname"))){
                    list.add(criteriaBuilder.like(root.get("nickname").as(String.class),(String) searchMap.get("nickname")));
                }
                if(searchMap.get("visits")!= null && !"".equals(searchMap.get("visits"))){
                    list.add(criteriaBuilder.equal(root.get("visits").as(String.class),(String) searchMap.get("visits")));
                }
                if(searchMap.get("thumbup")!= null && !"".equals(searchMap.get("thumbup"))){
                    list.add(criteriaBuilder.equal(root.get("thumbup").as(String.class),(String) searchMap.get("thumbup")));
                }
                if(searchMap.get("reply")!= null && !"".equals(searchMap.get("thumbup"))){
                    list.add(criteriaBuilder.equal(root.get("thumbup").as(String.class),(String) searchMap.get("thumbup")));
                }
                if(searchMap.get("solve")!= null && !"".equals(searchMap.get("solve"))){
                    list.add(criteriaBuilder.equal(root.get("solve").as(String.class),(String) searchMap.get("solve")));
                }
                if(searchMap.get("replyname")!= null && !"".equals(searchMap.get("replyname"))){
                    list.add(criteriaBuilder.like(root.get("replyname").as(String.class),(String) searchMap.get("replyname")));
                }
                if(searchMap.get("replytime")!= null && !"".equals(searchMap.get("replytime"))){
                    list.add(criteriaBuilder.like(root.get("replytime").as(String.class),(String) searchMap.get("replytime")));
                }
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
    }
}
