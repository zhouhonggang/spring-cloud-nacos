package com.zhou.javakc.system.role.service;

import com.zhou.javakc.common.entity.system.role.Role;
import com.zhou.javakc.common.jpa.base.service.BaseService;
import com.zhou.javakc.system.role.dao.RoleDao;
import org.springframework.stereotype.Service;

/**
 * 系统管理-角色管理-逻辑层(system-role)
 * @project spring-cloud-nacos
 * @author zhou
 * @version v0.2.0
 * @date 2020-02-03 10:24
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
@Service
public class RoleService extends BaseService<Role, RoleDao> {

}
