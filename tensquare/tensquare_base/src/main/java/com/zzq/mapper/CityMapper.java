package com.zzq.mapper;

import com.zzq.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author Zhang zq
 * @Date 2021/4/23 9:50
 * @Description
 */
public interface CityMapper extends JpaRepository<City,String>, JpaSpecificationExecutor<City> {
}
