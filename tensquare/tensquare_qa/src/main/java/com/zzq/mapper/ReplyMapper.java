package com.zzq.mapper;

import com.zzq.model.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author Zhang zq
 * @Date 2021/4/26 16:22
 * @Description
 */
public interface ReplyMapper extends JpaRepository<Reply,String>, JpaSpecificationExecutor<Reply> {

    /**
     * 根据问题id查询回答列表
     * @param problemId  问题id
     * @return   返回结果集
     */
    @Query(value = "select * from tb_reply where problemid = ?1",nativeQuery = true)
    List<Reply> queryProblemId(String problemId);

    /**
     * 我的回答列表
     * @param id  我的id
     * @return      返回结果信息
     */
    @Query(value = "select * from tb_reply where userid = ?1",nativeQuery = true)
    Page<Reply> queryByUserId(String id, Pageable pageable);
}
