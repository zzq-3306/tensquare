package com.zzq.mapper;

import com.zzq.model.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author Zhang zq
 * @Date 2021/4/22 20:09
 * @Description 使用SpringData
 *              JpaRepository  提供了基本的增删改查
 *              JpaSpecificationExecutor  用作复杂的条件查询
 */
public interface LabelMapper extends JpaRepository<Label,String>, JpaSpecificationExecutor<Label> {
}
