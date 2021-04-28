package com.zzq.service.impl;

import com.zzq.mapper.AdminMapper;
import com.zzq.model.Admin;
import com.zzq.model.AdminLogin;
import com.zzq.service.AdminService;
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
 * @Date 2021/4/25 21:53
 * @Description
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    /**
     * 添加管理员
     * @param admin  管理员信息
     */
    @Override
    public void add(Admin admin) {
        adminMapper.save(admin);
    }

    /**
     * 查询全部管理员
     * @return  返回结果集list
     */
    @Override
    public List<Admin> queryAll() {
        return adminMapper.findAll();
    }

    /**
     * 根据id   返回一个管理员
     * @param adminId  管理员id
     * @return    返回结果和状态
     */
    @Override
    public Admin queryById(String adminId) {
        return adminMapper.findById(adminId).get();
    }

    /**
     * 修改管理员信息
     * @param admin    管理员信息
     * @return       返回状态信息
     */
    @Override
    public void update(Admin admin) {
        adminMapper.save(admin);
    }

    /**
     * 根据id删除管理员
     * @param id   管理员id
     */
    @Override
    public void deleteById(String id) {
        adminMapper.deleteById(id);
    }

    /**
     * 条件查询 带分页
     * @param searchMap  查询条件
     * @param page     当前页
     * @param size     页面大小
     * @return      返回结果集
     */
    @Override
    public Page<Admin> queryLikeAndPage(Map searchMap, int page, int size) {
        Specification<Admin> specification = createSpecification(searchMap);
        PageRequest of = PageRequest.of(page, size);
        return adminMapper.findAll(specification,of);
    }

    /**
     * 管理员登陆
     * @param login  管理员登陆的信息
     * @return     返回状态信息
     */
    @Override
    public Admin queryByNameAndPassword(AdminLogin login) {
        return adminMapper.queryOne(login);
    }

    /**
     * jpa的复杂查询
     * @param searchMap    搜索条件
     * @return           返回的是Specification<Admin>
     */
    private Specification<Admin> createSpecification(Map searchMap){
        return new Specification<Admin>() {
            @Override
            public Predicate toPredicate(Root<Admin> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                ArrayList<Predicate> list = new ArrayList<>();
                if (searchMap.get("id")!= null && !"".equals(searchMap.get("id"))){
                    list.add(criteriaBuilder.equal(root.get("id").as(String.class),(String)searchMap.get("id")));
                }
                if (searchMap.get("loginname")!= null && !"".equals(searchMap.get("loginname"))){
                    list.add(criteriaBuilder.like(root.get("loginname").as(String.class),(String)searchMap.get("loginname")));
                }
                 if (searchMap.get("state")!= null && !"".equals(searchMap.get("state"))){
                    list.add(criteriaBuilder.equal(root.get("state").as(String.class),(String)searchMap.get("state")));
                }
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
    }
}
