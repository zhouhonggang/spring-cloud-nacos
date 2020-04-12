package com.zhou.javakc.system.user.controller;

import com.zhou.javakc.common.entity.system.user.User;
import com.zhou.javakc.system.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.util.List;
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
@RequestMapping("system/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String query()
    {
        return "系统管理-用户模块-查询功能";
    }

    @PostMapping("query")
    public Page<User> query(@RequestBody User entity)
    {
        return userService.findAll(entity);
    }

    @PostMapping("photo")
    public String photo(@RequestParam("photo") MultipartFile photo)
    {
        String name = UUID.randomUUID().toString();
        try {
            String path = ResourceUtils.getURL("classpath:").getPath();
            File photoFile = new File(path + File.separator + name);
            photo.transferTo(photoFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }

    @PostMapping
    public void create(@RequestBody @Valid User entity)
    {
        userService.save(entity);
    }

    @GetMapping("{id}")
    public Map<String, Object> load(@PathVariable String id)
    {
        return userService.queryById(id);
    }

    @PutMapping
    public void update(@RequestBody User entity)
    {
        userService.update(entity);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id)
    {
        userService.delete(id);
    }

}
