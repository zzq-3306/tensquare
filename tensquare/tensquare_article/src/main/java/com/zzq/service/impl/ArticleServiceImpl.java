package com.zzq.service.impl;

import com.zzq.mapper.ArticleMapper;
import com.zzq.model.Article;
import com.zzq.service.ArticleService;
import org.assertj.core.util.Lists;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

/**
 * @Author Zhang zq
 * @Date 2021/4/28 21:03
 * @Description
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 添加文章
     * @param article  文章信息
     */
    @Override
    public void add(Article article) {
       articleMapper.save(article);
    }

    /**
     * 查询全部
     * @return   返回结果集
     */
    @Override
    public List<Article> queryAll() {
        Iterable<Article> all = articleMapper.findAll();
        ArrayList<Article> list = Lists.newArrayList();
        all.forEach(list::add);
        System.out.println(list+"----------");
        return list;
    }

    /**
     * 根据id查询
     * @param articleId   文章id
     * @return            返回对应的文章信息
     */
    @Override
    public Article queryById(String articleId) {
        //先从redis中拿
        Article article = (Article) redisTemplate.opsForValue().get("article_" + articleId);
        if (article == null){
            //redis中没有
            article = articleMapper.findById(articleId).get();
            //放入到缓存redis中   设置过期时间为1天
            redisTemplate.opsForValue().set("article_"+articleId,article,1, TimeUnit.DAYS);
        }
        return article;
    }

    /**
     * 修改文章信息
     * @param article       文章信息
     * @param articleId    文章的id
     */
    @Override
    public void updateById(Article article, String articleId) {
        article.setId(articleId);
        //删除缓存
        redisTemplate.delete("article_"+articleId);
        articleMapper.save(article);
    }


    /**
     * 根据id删除文章信息
     * @param articleId  文章信息id
     */
    @Override
    public void deleteById(String articleId) {
        //删除缓存
        redisTemplate.delete("article_id"+articleId);
        articleMapper.deleteById(articleId);
    }

    /**
     * 根据条件查询全部文章信息
     * @param article    文章信息
     * @return           返回结果集
     */
    @Override
    public List<Article> search(Article article) {
        /**
         *   SearchQuery searchQuery = new NativeSearchQueryBuilder()
         *   .withQuery(QueryBuilders.boolQuery().must(termQuery("userId", userId))
         *  .should(rangeQuery("weight").lt(weight)).must(matchQuery("title", title))).build();
         *         return elasticsearchTemplate.queryForList(searchQuery, Post.class);
         */
//       new NativeSearchQueryBuilder().withQuery(QueryBuilders.boolQuery().must(termQuery("id",article.getId())))
        return null;
    }
}
