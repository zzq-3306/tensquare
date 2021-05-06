package com.zzq.mapper;

import com.zzq.model.Article;
import com.zzq.model.Channel;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author Zhang zq
 * @Date 2021/4/28 21:01
 * @Description
 */
public interface ArticleMapper extends ElasticsearchRepository<Article,String> {
}
