package com.zhou.javakc.system.user.dao;

import com.zhou.javakc.common.entity.system.user.User;
import com.zhou.javakc.common.jpa.base.dao.BaseDao;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

/**
 * 系统管理-用户管理-数据层(system-user)
 * @project spring-cloud-nacos
 * @author zhou
 * @version v0.2.0
 * @date 2020-02-03 10:24
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
public interface UserDao extends BaseDao<User, String> {

    @Query(" select new map(id as id, nickname as nickname, phone as phone, email as email) from User where id = ?1 ")
    public Map<String, Object> queryById(String id);

}
