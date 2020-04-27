package com.zhou.javakc.system.role.controller;

import com.zhou.javakc.common.entity.system.role.Role;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统管理-角色管理-表现层(system-role)
 * @project spring-cloud-nacos
 * @author zhou
 * @version v0.2.0
 * @date 2020-02-03 10:24
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
@RestController
@Api(tags = "系统管理-角色管理-API接口")
public class RoleController {

    @ApiOperation(value = "角色获取", notes = "根据角色主键ID获取角色基本信息!")
    @GetMapping("{id}")
    public Role load(@PathVariable String id)
    {
        System.out.println("根据ID获取");

        Role role = new Role();
        role.setId("roleId001");
        role.setRolename("角色名称002");
        role.setRolecode("角色编号003");
        role.setSummary("summary004");
        return role;
    }

    @ApiOperation(value = "角色删除", notes = "根据角色主键ID删除角色基本信息!")
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id)
    {
        System.out.println("根据ID删除");
        throw new NullPointerException();
    }

}
