package com.zhou.javakc.common.entity.system.role;

import com.zhou.javakc.common.entity.base.Base;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 公共组件(系统管理-角色管理)
 * @project spring-cloud-nacos
 * @author zhou
 * @version v0.2.0
 * @date 2020-02-03 10:24
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
@Getter
@Setter
@Entity
@Table(name = "system_role")
public class Role extends Base {
    @Column(name = "role_name")
    private String rolename;

    @Column(name = "role_code")
    private String rolecode;

    @Column(name = "summary")
    private String summary;
}
