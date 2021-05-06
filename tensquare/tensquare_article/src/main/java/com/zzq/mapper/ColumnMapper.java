package com.zzq.mapper;

import com.zzq.model.Column;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * @Author Zhang zq
 * @Date 2021/4/28 21:02
 * @Description
 */
public interface ColumnMapper extends ElasticsearchRepository<Column,String> {
}
