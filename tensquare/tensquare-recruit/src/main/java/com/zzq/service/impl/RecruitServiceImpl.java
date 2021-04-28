package com.zzq.service.impl;

import com.zzq.mapper.RecruitMapper;
import com.zzq.model.Recruit;
import com.zzq.service.RecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Zhang zq
 * @Date 2021/4/28 16:43
 * @Description
 */
@Service
public class RecruitServiceImpl implements RecruitService {

    @Autowired
    private RecruitMapper recruitMapper;

    /**
     * 增加招聘
     * @param recruit    招聘信息
     */
    @Override
    public void add(Recruit recruit) {
        recruitMapper.save(recruit);
    }

    /**
     * 招聘全部列表
     */
    @Override
    public List<Recruit> queryAll() {
        return recruitMapper.findAll();
    }

    /**
     * 根据id查询招聘
     * @param recruitId  招聘id
     * @return         返回结果
     */
    @Override
    public Recruit queryById(String recruitId) {
        return recruitMapper.findById(recruitId).get();
    }

    /**
     * 修改招聘
     * @param recruit   招聘信息
     * @param recruitId   招聘id
     */
    @Override
    public void updateById(Recruit recruit, String recruitId) {
        recruit.setId(recruitId);
        recruitMapper.save(recruit);
    }


    /**
     * 根据id删除招聘信息
     * @param recruitId  招聘id
     */
    @Override
    public void deleteById(String recruitId) {
        recruitMapper.deleteById(recruitId);
    }

    /**
     * 根据条件查询招聘列表
     * @param recruit   条件
     * @return         返回结果list
     */
    @Override
    public List<Recruit> search(Recruit recruit) {
        Example<Recruit> of = Example.of(recruit);
        return recruitMapper.findAll(of);
    }

    /**
     * 招聘分页
     * @param recruit  搜索条件
     * @param page     当前页
     * @param size     页面大小
     * @return         返回结果list
     */
    @Override
    public Page<Recruit> pageAndLike(Recruit recruit, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Example<Recruit> example = Example.of(recruit);
        return recruitMapper.findAll(example,pageRequest);
    }

    /**
     * 推荐职位   查询状态为2并以创建时间降序排序  查询前4条信息
     * @return   返回结果list
     */
    @Override
    public List<Recruit> recommend() {
        return recruitMapper.findTop4ByStateOrderByCreatetimeDesc("2");
    }

    /**
     * 最新职位   查询状态不为0并以创建时间降序排序  查询前12条
     * @return  返回结果list
     */
    @Override
    public List<Recruit> newList() {
        return recruitMapper.findTop12ByStateNotOrderByCreatetimeDesc("0");
    }
}
