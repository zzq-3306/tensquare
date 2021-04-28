package com.zzq.mapper;

import com.zzq.model.Spit;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author Zhang zq
 * @Date 2021/4/27 11:23
 * @Description
 */
public interface SpitMapper extends MongoRepository<Spit,String> {


}
