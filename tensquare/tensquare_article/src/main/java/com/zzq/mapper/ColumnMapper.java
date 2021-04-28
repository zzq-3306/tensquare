package com.zzq.mapper;

import com.zzq.model.Column;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author Zhang zq
 * @Date 2021/4/28 21:02
 * @Description
 */
public interface ColumnMapper extends JpaRepository<Column,String>, JpaSpecificationExecutor<Column> {
}
