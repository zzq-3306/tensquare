package com.zzq.controller;

import com.zzq.model.Article;
import com.zzq.service.ArticleService;
import model.Result;
import model.StatusCode;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * @Author Zhang zq
 * @Date 2021/4/28 21:06
 * @Description
 */
@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 添加文章
     *
     * @param article 文章信息
     */
    @PostMapping()
    public Result add(@RequestBody Article article) {
        articleService.add(article);
        return new Result(true, StatusCode.OK, "成功");
    }

    /**
     * 查询全部
     *
     * @return 返回结果集
     */
    @GetMapping
    public Result queryAll() {
        List<Article> list = articleService.queryAll();
        return new Result(true, StatusCode.OK, "成功", list);
    }

    /**
     * 根据id查询
     *
     * @param articleId 文章id
     * @return 返回对应的文章信息
     */
    @GetMapping("/{articleId}")
    public Result queryById(@PathVariable String articleId) {
        Article article = articleService.queryById(articleId);
        return new Result(true, StatusCode.OK, "成功", article);
    }

    /**
     * 修改文章信息
     *
     * @param article   文章信息
     * @param articleId 文章的id
     * @return 返回状态信息
     */
    @PutMapping("/{articleId}")
    public Result updateById(@RequestBody Article article, @PathVariable String articleId) {
        articleService.updateById(article, articleId);
        return new Result(true, StatusCode.OK, "成功");
    }

    /**
     * 根据id删除文章信息
     *
     * @param articleId 文章信息id
     * @return 返回状态信息
     */
    @DeleteMapping("/{articleId}")
    public Result deleteById(@PathVariable String articleId) {
        articleService.deleteById(articleId);
        return new Result(true, StatusCode.OK, "成功");
    }


    /**
     * 根据条件查询全部文章信息
     *
     * @param article 文章信息
     * @return 返回结果集
     */
    @PostMapping("/search")
    public Result search(@RequestBody Article article) {
        List<Article> list = articleService.search(article);
        return new Result(true, StatusCode.OK, "成功", list);
    }

    
//    @RequestMapping("/bool")
//    public Object bool(String title, Integer userId, Integer weight) {
//        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.boolQuery().must(termQuery("userId", userId))
//                .should(rangeQuery("weight").lt(weight)).must(matchQuery("title", title))).build();
//        return elasticsearchTemplate.queryForList(searchQuery, Post.class);
//    }
}
