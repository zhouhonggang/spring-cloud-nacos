package com.zhou.javakc.system.user.service;

import com.zhou.javakc.common.entity.system.role.Role;
import com.zhou.javakc.common.entity.system.user.User;
import com.zhou.javakc.common.jpa.base.service.BaseService;
import com.zhou.javakc.common.jpa.dynamic.criterion.Criteria;
import com.zhou.javakc.common.jpa.dynamic.criterion.Criterion;
import com.zhou.javakc.common.jpa.dynamic.restrictions.Restrictions;
import com.zhou.javakc.system.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 系统管理-用户管理-逻辑层(system-user)
 * @project spring-cloud-nacos
 * @author zhou
 * @version v0.2.0
 * @date 2020-02-03 10:24
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
@Service
public class UserService extends BaseService<User, UserDao> {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Page<User> findAll(User entity)
    {
        Criteria<User> criteria = new Criteria<>();
        // 账号精确搜索
        if(!StringUtils.isEmpty(entity.getUsername()))
        {
            criteria.add(Restrictions.eq("username", entity.getUsername()));
        }
        // 昵称模糊搜索
        if(!StringUtils.isEmpty(entity.getNickname()))
        {
            criteria.add(Restrictions.like("nickname", entity.getNickname()));
        }
        // 电话模糊搜索
        if(!StringUtils.isEmpty(entity.getPhone()))
        {
            criteria.add(Restrictions.like("phone", entity.getPhone()));
        }
        // 邮件模糊搜索
        if(!StringUtils.isEmpty(entity.getEmail()))
        {
            criteria.add(Restrictions.like("email", entity.getEmail()));
        }
        // 角色搜索
        if(!StringUtils.isEmpty(entity.getRoles()) && entity.getRoles().size() == 1)
        {
            // 角色code等值搜索
            if(!StringUtils.isEmpty(entity.getRoles().get(0).getRolecode()))
            {
                criteria.add(Restrictions.joinList("roles", "rolecode", entity.getRoles().get(0).getRolecode(), Criterion.Operator.eq));
            }
            // 角色name模糊搜索
            if(!StringUtils.isEmpty(entity.getRoles().get(0).getRolename()))
            {
                criteria.add(Restrictions.joinList("roles", "rolename", entity.getRoles().get(0).getRolename(), Criterion.Operator.like));
            }
        }
        return dao.findAll(criteria, PageRequest.of(entity.getOffset(), entity.getLimit()));
    }

    @Transactional(readOnly = false)
    public User save(User entity)
    {
        // 解析上传的头像, 存入到数据库
        String path = null;
        try {
            path = ResourceUtils.getURL("classpath:").getPath();
            File file = new File(path + File.separator + entity.getPhotoname());
            byte[] photo = FileCopyUtils.copyToByteArray(file);
            entity.setPhoto(photo);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 密码加密后存入到数据库
        entity.setPassword( passwordEncoder.encode(entity.getPassword()) );
        return dao.save(entity);
    }

    public Map<String, Object> queryById(String id)
    {
        return dao.queryById(id);
    }

    @Transactional(readOnly = false)
    public User update(User entity)
    {
        return dao.save(entity);
    }

}
