package com.zhou.javakc.security.oauth2.service;

import com.zhou.javakc.security.oauth2.dao.UserDetailsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * XXX 类描述信息
 *
 * @author zhou
 * @version v1.0.0
 * @project
 * @date 2019-12-04 14:51
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDetailsDao userDetailsDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //验证登陆用户
        Map<String, Object> user = userDetailsDao.loadUserByUsername(s);
        if(StringUtils.isEmpty(user))
        {
            throw new UsernameNotFoundException("username:  " + s + "is not exist!");
        }

        //获取用户权限
        List<GrantedAuthority> authorities = new ArrayList<>();

        //返回认证用户
        return new User(s, user.get("password").toString(), authorities);
    }

}
