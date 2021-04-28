package com.zzq.mapper;

import com.zzq.model.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * @Author Zhang zq
 * @Date 2021/4/26 12:01
 * @Description
 */
public interface ProblemMapper extends JpaRepository<Problem,String>, JpaSpecificationExecutor<Problem> {

    /**
     * 热门列表查询    按照回复数降序
     * @param label  标签id
     * @return        返回问题列表
     */
    @Query(value = "select * from tb_problem p where id in (select problemid from tb_pl where labelid = ?1 ) order by reply desc",nativeQuery = true)
    Page<Problem> findHotListByLabelId(String label,Pageable pageable);


    /**
     * 最新问答列表    按照时间降序
     * @param labelId     标签Id  为0则查询全部
     * @param pageable       当前页
     * @return          结果集
     */
    @Query(value = "select * from tb_problem where id in (select problemid from tb_pl where labelid = ?1) order by replytime desc",nativeQuery = true)
    Page<Problem> findNewListByLabelId(String labelId, Pageable pageable);

    /**
     * 等待回答列表
     * @param label  标签id
     * @param pageable
     * @return          返回状态结果
     */
    @Query(value = "select * from tb_problem where id in (select problemid from tb_pl where labelid = ?1) and reply = 0 order by createtime desc",nativeQuery = true )
    Page<Problem> findWaitListByLabelId(String label, Pageable pageable);

    /**
     * 根据id修改浏览量和回复数
     * @param id  问题id
     * @return     true   false
     */
    @Transactional
    @Modifying
    @Query(value = "update tb_problem set visits = visits+1 , reply=reply+1 where id =?1 ",nativeQuery = true)
    int updateById(String id);
}
