package com.zzq.service;

import com.zzq.model.Reply;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * @Author Zhang zq
 * @Date 2021/4/26 16:21
 * @Description
 */
public interface ReplyService {

    /**
     * 增加回答
     * @param reply  回答信息
     */
    void add(Reply reply);

    /**
     * 回答全部列表
     * @return  返回结果集
     */
    List<Reply> queryAll();

    /**
     * 根据id查询回答信息
     * @param replyId   回答id
     * @return      返回结果
     */
    Reply queryById(String replyId);

    /**
     * 修改reply
     * @param reply   回答详情
     */
    void update(Reply reply);

    /**
     * 根据id删除
     * @param replyId  回答信息id
     */
    void delete(String replyId);

    /**
     * 回答分页
     * @param searchMap  搜索条件
     * @param page       当前页
     * @param size       页面大小
     * @return        返回结果集
     */
    Page<Reply> searchPageAndLike(Map searchMap, int page, int size);

    /**
     * 根据问题id查询回答列表
     * @param problemId  问题id
     * @return   返回结果集
     */
    List<Reply> queryByProblemId(String problemId);

    /**
     * 回答问题
     * @param reply  回答信息详情
     */
    void saveReply(Reply reply);

    /**
     * 我的回答列表
     * @param page  当前页
     * @param size   页面大小
     * @return      返回结果信息
     */
    Page<Reply> myList(int page, int size);
}
