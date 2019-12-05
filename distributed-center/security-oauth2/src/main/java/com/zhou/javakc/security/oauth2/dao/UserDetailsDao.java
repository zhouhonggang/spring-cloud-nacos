package com.zhou.javakc.security.oauth2.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Map;

/**
 * XXX 类描述信息
 *
 * @author zhou
 * @version v1.0.0
 * @project
 * @date 2019-12-04 14:53
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
@Repository
public class UserDetailsDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDetailsDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Map<String, Object> loadUserByUsername(String username)
    {
        String sql = " select u.username as username, u.password as password from user u where u.username = ? ";
        return jdbcTemplate.queryForMap(sql, username);
    }

}
