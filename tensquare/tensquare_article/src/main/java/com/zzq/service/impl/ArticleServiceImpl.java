package com.zzq.service.impl;

import com.zzq.mapper.ArticleMapper;
import com.zzq.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Zhang zq
 * @Date 2021/4/28 21:03
 * @Description
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;


}
