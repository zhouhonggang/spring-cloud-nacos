package com.zhou.javakc.system.user.controller;

import com.zhou.javakc.common.entity.system.user.User;
import com.zhou.javakc.system.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.util.Map;
import java.util.UUID;

/**
 * 系统管理-用户管理-表现层(system-user)
 * @project spring-cloud-nacos
 * @author zhou
 * @version v0.2.0
 * @date 2020-02-03 10:24
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
@RestController
@Api(tags = "系统管理-人员管理-API接口")
public class UserController {

    @Value("${images.path}")
    private String imagesPath;
    @Autowired
    private UserService userService;

    @PostMapping
    @ApiOperation(value = "用户注册", notes = "请按照校验规则添加必填内容及相应格式!")
    @ApiImplicitParams({
            @ApiImplicitParam(name="username", value="登录账号", required=true),
            @ApiImplicitParam(name="password", value="登陆密码", required=true),
            @ApiImplicitParam(name="nickname", value="用户昵称", required=true),
            @ApiImplicitParam(name="phone", value="手机号码", required=true),
            @ApiImplicitParam(name="email", value="邮箱地址", required=true)
    })
    public User create(@RequestBody User entity)
    {
        return userService.save(entity);
    }

    @ApiOperation(value = "用户查询", notes = "根据动态查询条件分页查询!")
    @PostMapping("query")
    public Page<User> query(@RequestBody User entity)
    {
        return userService.findAll(entity);
    }

    @ApiOperation(value = "头像上传", notes = "用户注册页面, 涉及到用户头像上传的API!")
    @PostMapping("photo")
    public String photo(@RequestParam("photo") MultipartFile photo)
    {
        String name = photo.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String path = uuid + name.substring(name.lastIndexOf("."));
        try {
            File photoFile = new File(imagesPath + path);
            photo.transferTo(photoFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }

    @ApiOperation(value = "用户修改", notes = "请按照校验规则添加必填内容及相应格式!")
    @PutMapping
    public User update(@RequestBody User entity)
    {
        return userService.update(entity);
    }

    @ApiOperation(value = "用户主键查询", notes = "根据用户主键ID查询用户基本信息!")
    @GetMapping("{id}")
    public Map<String, Object> load(@PathVariable String id)
    {
        return userService.queryById(id);
    }

    @ApiOperation(value = "用户删除", notes = "根据用户主键ID删除用户基本信息!")
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id)
    {
        userService.delete(id);
    }

}
