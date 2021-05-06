package com.zzq.mapper;


import com.zzq.model.Channel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * @Author Zhang zq
 * @Date 2021/4/28 21:01
 * @Description
 */
public interface ChannelMapper extends ElasticsearchRepository<Channel,String> {
}
