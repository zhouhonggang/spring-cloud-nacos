package com.zhou.javakc.common.entity.listener;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

/**
 * 公共组件(监听jpa增删改的动作)
 * @project spring-cloud-nacos
 * @author zhou
 * @version v0.2.0
 * @date 2020-02-03 10:24
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
public class CustomUserListener {

    @PrePersist
    public void prePersist(Object source)
    {
        System.out.println("监听SpringDataJpa准备写入之前数据库操作!");
    }

    @PreUpdate
    public void preUpdate(Object source)
    {
        System.out.println("监听SpringDataJpa准备修改之前数据库操作!");
    }

    @PreRemove
    public void preRemove(Object source)
    {
        System.out.println("监听SpringDataJpa准备删除之前数据库操作!");
    }

}
