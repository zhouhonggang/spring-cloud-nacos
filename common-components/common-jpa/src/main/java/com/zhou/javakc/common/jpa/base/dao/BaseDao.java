package com.zhou.javakc.common.jpa.base.dao;

import com.zhou.javakc.common.entity.base.Base;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * 公共组件(底层数据层接口) 封装了公共的CRUD接口
 * @project spring-cloud-nacos
 * @author zhou
 * @version v0.2.0
 * @date 2020-02-07 10:24
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
@NoRepositoryBean
public interface BaseDao<T extends Base, ID extends Serializable> extends JpaRepository<T, ID> {

    /**
     * 动态参数查询
     * @param specification 动态查询条件
     * @return 结果集
     */
    List<T> findAll(Specification<T> specification);

    /**
     * 动态参数分页查询
     * @param specification 动态参数
     * @param pageable 分页参数
     * @return 结果集
     */
    Page<T> findAll(Specification<T> specification, Pageable pageable);

}
