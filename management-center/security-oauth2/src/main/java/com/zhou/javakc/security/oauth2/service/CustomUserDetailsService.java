package com.zhou.javakc.security.oauth2.service;

import com.zhou.javakc.security.oauth2.entity.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户认证查询(Spring Cloud Security Oauth2)
 * @project spring-cloud-nacos
 * @author zhou
 * @version v1.0.0
 * @date 2019-12-03 10:24
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private DataSource dataSource;

    @Bean
    private JdbcOperations jdbcOperations()
    {
        return new JdbcTemplate(dataSource);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String sql = " select id, user_name, pass_word, nick_name, user_photo from spring_cloud_nacos.system_user where user_name = ? ";
        List<SystemUser> list = jdbcOperations().query(sql, new RowMapper<SystemUser>() {
            @Override
            public SystemUser mapRow(ResultSet rs, int i) throws SQLException {
                //权限列表
                List<GrantedAuthority> authorities = new ArrayList<>();
                String name = rs.getString("user_name");
                String pass = rs.getString("pass_word");

                SystemUser user = new SystemUser(name, pass, authorities);
                user.setUserid(rs.getInt("id"));
                user.setNickname(rs.getString("nick_name"));
                user.setUserphoto(rs.getBytes("user_photo"));

                return user;
            }
        }, username);
        if(StringUtils.isEmpty(list) || list.size() == 0)
        {
            throw new UsernameNotFoundException("账号:  " + username + "不存在!");
        }
        //返回认证用户
        return list.get(0);
    }

}
