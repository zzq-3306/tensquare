package com.zzq.service;

import com.zzq.model.Article;

import java.util.List;

/**
 * @Author Zhang zq
 * @Date 2021/4/28 21:03
 * @Description
 */
public interface ArticleService {

    /**
     * 添加文章
     * @param article  文章信息
     */
    void add(Article article);

    /**
     * 查询全部
     * @return   返回结果集
     */
    List<Article> queryAll();

    /**
     * 根据id查询
     * @param articleId   文章id
     * @return            返回对应的文章信息
     */
    Article queryById(String articleId);

    /**
     * 修改文章信息
     * @param article       文章信息
     * @param articleId    文章的id
     */
    void updateById(Article article, String articleId);

    /**
     * 根据id删除文章信息
     * @param articleId  文章信息id
     */
    void deleteById(String articleId);

    /**
     * 根据条件查询全部文章信息
     * @param article    文章信息
     * @return           返回结果集
     */
    List<Article> search(Article article);
}
