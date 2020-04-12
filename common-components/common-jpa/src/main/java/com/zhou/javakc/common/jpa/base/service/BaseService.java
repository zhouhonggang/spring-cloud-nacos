package com.zhou.javakc.common.jpa.base.service;

import com.zhou.javakc.common.entity.base.Base;
import com.zhou.javakc.common.jpa.base.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * 公共组件(底层逻辑层层) 封装了公共的CRUD实现
 * @project spring-cloud-nacos
 * @author zhou
 * @version v0.2.0
 * @date 2020-02-07 10:28
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
@Transactional(readOnly = true)
public abstract class BaseService<T extends Base, D extends BaseDao>{

    @Autowired
    protected D dao;

    /**
     * 对象保存/修改方法
     * @param entity 对象
     * @return 成功的对象
     */
    @Transactional(readOnly = false)
    public T save(T entity)
    {
        return (T)dao.save(entity);
    }

    /**
     * 对象删除方法
     * @param id 主键
     */
    @Transactional(readOnly = false)
    public void delete(Serializable id)
    {
        dao.deleteById(id);
    }

    /**
     * 对象删除方法
     * @param entity 对象
     */
    @Transactional(readOnly = false)
    public void delete(T entity)
    {
        dao.delete(entity);
    }

    /**
     * 对象批量删除方法
     * @param entitys 批量集合
     */
    @Transactional(readOnly = false)
    public void delete(List<T> entitys)
    {
        dao.deleteInBatch(entitys);
    }

    /**
     * 根据id获取对象
     * @param id 主键
     * @return 对象
     */
    public T queryById(Serializable id)
    {
        return (T)dao.findById(id).get();
    }

}
