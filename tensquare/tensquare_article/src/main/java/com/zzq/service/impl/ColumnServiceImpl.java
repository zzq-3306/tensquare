package com.zzq.service.impl;

import com.zzq.mapper.ArticleMapper;

import com.zzq.mapper.ColumnMapper;
import com.zzq.service.ColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Author Zhang zq
 * @Date 2021/4/28 21:03
 * @Description
 */
@Service
public class ColumnServiceImpl implements ColumnService {

    @Autowired
    private ColumnMapper columnMapper;


}
