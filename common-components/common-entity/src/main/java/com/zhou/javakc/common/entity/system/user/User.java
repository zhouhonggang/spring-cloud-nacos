package com.zhou.javakc.common.entity.system.user;

import com.zhou.javakc.common.entity.base.Base;
import com.zhou.javakc.common.entity.system.role.Role;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * 公共组件(系统管理-用户管理)
 * @project spring-cloud-nacos
 * @author zhou
 * @version v0.2.0
 * @date 2020-02-03 10:24
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
@Getter
@Setter
@Entity
@Table(name = "system_user")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User extends Base {

    /** 登陆账号 */
    @Column(name = "user_name", updatable = false)
    @ApiModelProperty(value = "登陆名称 | Record user username")
    private String username;

    /** 登陆密码 */
    @Column(name = "pass_word", updatable = false)
    @ApiModelProperty(value = "登陆密码 | Record user password")
    private String password;

    /** 用户昵称 */
    @Column(name = "nick_name")
    @ApiModelProperty(value = "用户昵称 | Record user nickname")
    private String nickname;

    /** 手机号码 */
    @Column(name = "user_phone")
    @ApiModelProperty(value = "手机号码 | Record user phone")
    private String phone;

    /** 电子邮箱 */
    @Column(name = "user_email")
    @ApiModelProperty(value = "邮箱地址 | Record user email")
    private String email;

    /** 用户头像 */
    @Column(name = "user_photo", updatable = false)
    @ApiModelProperty(value = "用户头像 | Record user photo")
    private String photo;

    @ManyToMany
    @JoinTable(
        name = "system_user_role",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @ApiModelProperty(value = "角色集合 | Record user role list")
    private List<Role> roles;

}
