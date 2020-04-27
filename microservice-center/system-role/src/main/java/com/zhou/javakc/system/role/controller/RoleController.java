package com.zhou.javakc.system.role.controller;

import com.zhou.javakc.common.entity.system.role.Role;
import com.zhou.javakc.system.role.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "角色获取", notes = "根据角色主键ID获取角色基本信息!")
    @GetMapping("{id}")
    public Role load(@PathVariable String id)
    {
        return roleService.queryById(id);
    }

    @ApiOperation(value = "角色删除", notes = "根据角色主键ID删除角色基本信息!")
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id)
    {
        roleService.delete(id);
    }

    @ApiOperation(value = "角色添加", notes = "注册角色基本信息!")
    @PostMapping
    public void save(Role entity)
    {
        roleService.save(entity);
    }

    @ApiOperation(value = "角色修改", notes = "修改角色基本信息!")
    @PutMapping
    public void update(Role entity)
    {
        roleService.save(entity);
    }

}
