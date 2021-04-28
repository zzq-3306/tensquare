package com.zzq.service.impl;

import com.zzq.mapper.EnterpriseMapper;
import com.zzq.model.Enterprise;
import com.zzq.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Zhang zq
 * @Date 2021/4/28 16:56
 * @Description
 */
@Service
public class EnterpriseServiceImpl implements EnterpriseService {

    @Autowired
    private EnterpriseMapper enterpriseMapper;
    /**
     * 增加企业
     * @param enterprise 企业信息
     */
    @Override
    public void add(Enterprise enterprise) {
        enterpriseMapper.save(enterprise);
    }

    /**
     * 企业全部列表
     * @return  返回结果集
     */
    @Override
    public List<Enterprise> queryAll() {
        return enterpriseMapper.findAll();
    }

    /**
     * 根据id查询企业
     * @param enterpriseId  企业的id
     * @return      返回企业的信息
     */
    @Override
    public Enterprise queryById(String enterpriseId) {
        return enterpriseMapper.findById(enterpriseId).get();
    }

    /**
     * 修改企业
     * @param enterpriseId  企业的id
     */
    @Override
    public void updateById(String enterpriseId,Enterprise enterprise) {
        enterprise.setId(enterpriseId);
        enterpriseMapper.save(enterprise);
    }

    /**
     * 根据id删除企业
     * @param enterpriseId  企业的id
     */
    @Override
    public void deleteById(String enterpriseId) {
        enterpriseMapper.deleteById(enterpriseId);
    }

    /**
     * 根据条件查询企业列表
     * @param enterprise  搜索条件
     * @return        返回结果集
     */
    @Override
    public List<Enterprise> search(Enterprise enterprise) {
        Example<Enterprise> example = Example.of(enterprise);
        return enterpriseMapper.findAll(example);
    }

    /**
     * 热门企业列表    1热门
     * @return   返回结果集
     */
    @Override
    public List<Enterprise> hotList() {
        return enterpriseMapper.findByIshot("1");
    }

    /**
     * enterprise 分页模糊查询
     * @param enterprise  搜索条件
     * @param page        当前页
     * @param size        页面大小
     * @return            返回结果集
     */
    @Override
    public Page<Enterprise> pageAndLike(Enterprise enterprise, int page, int size) {
        ExampleMatcher exampleMatcher = getExampleMatcher();
        Example<Enterprise> example = Example.of(enterprise, exampleMatcher);
        PageRequest pageRequest = PageRequest.of(page, size);
        return enterpriseMapper.findAll(example,pageRequest);
    }

    /**
     * 自定义规则
     * @return  返回 ExampleMatcher
     */
    private ExampleMatcher getExampleMatcher(){
        //自定义匹配器规则
        return ExampleMatcher.matching()
                .withMatcher("id", matcher -> matcher.exact())
                .withMatcher("name", matcher -> matcher.contains())
                .withMatcher("summary", matcher -> matcher.contains())
                .withMatcher("address", matcher -> matcher.contains())
                .withMatcher("labels", matcher -> matcher.contains())
                .withMatcher("coordinate", matcher -> matcher.contains())
                .withMatcher("ishot", matcher -> matcher.contains())
                .withMatcher("logo", matcher -> matcher.contains())
                .withMatcher("jobcount", matcher -> matcher.contains())
                .withMatcher("url", matcher -> matcher.contains());
    }
}
