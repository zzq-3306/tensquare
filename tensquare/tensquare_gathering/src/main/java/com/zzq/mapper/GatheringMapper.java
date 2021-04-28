package com.zzq.mapper;

import com.zzq.model.Gathering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author Zhang zq
 * @Date 2021/4/28 9:45
 * @Description
 */
public interface GatheringMapper extends JpaRepository<Gathering,String>, JpaSpecificationExecutor<Gathering> {

}
