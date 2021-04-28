package com.zzq.mapper;

import com.zzq.model.Article;
import com.zzq.model.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author Zhang zq
 * @Date 2021/4/28 21:01
 * @Description
 */
public interface ArticleMapper extends JpaRepository<Article,String>, JpaSpecificationExecutor<Article> {
}