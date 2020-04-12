package com.zhou.javakc.common.entity.system.user;

import com.zhou.javakc.common.entity.base.Base;
import com.zhou.javakc.common.entity.system.role.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
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
//    @NotBlank(message = "账号不能为空")
//    @Size(min = 3, max = 16, message = "账号的长度不能低于{min}且不能高于{max}")
    @Column(name = "user_name", updatable = false)
    private String username;

    /** 登陆密码 */
//    @NotBlank(message = "密码不能为空")
//    @Size(min = 6, max = 16, message = "密码的长度不能低于{min}且不能高于{max}")
    @Column(name = "pass_word", updatable = false)
    private String password;

    /** 用户昵称 */
    @NotBlank(message = "昵称不能为空")
    @Column(name = "nick_name")
    private String nickname;

    /** 手机号码 */
    @Pattern(regexp = "^[1][3,4,5,7,8][0-9]{9}$", message = "请输入正确的手机号码")
    @Column(name = "phone")
    private String phone;

    /** 电子邮箱 */
    @Email(message = "请输入正确的邮箱格式")
    @Column(name = "email")
    private String email;

    /** 用户头像 */
    @Lob
    @Column(name = "user_photo", updatable = false)
    private byte[] photo;

    /** 头像临时传输 */
    @Transient
    private String photoname;

    @ManyToMany
    @JoinTable(
        name = "system_user_role",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;
}
