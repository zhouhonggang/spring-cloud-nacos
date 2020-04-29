package com.zhou.javakc.common.entity.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zhou.javakc.common.entity.listener.CustomUserListener;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 公共组件(底层实体类) 封装了公共的属性
 * @project spring-cloud-nacos
 * @author zhou
 * @version v0.2.0
 * @date 2020-02-03 10:24
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class, CustomUserListener.class})
public class Base implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据库主键ID
     * 采用hibernate的uuid生成32位字符串
     */
    @Id
    @Column(name = "id", length = 32)
    @GeneratedValue(generator = "generator_uuid")
    @GenericGenerator(name = "generator_uuid", strategy = "uuid")
    @ApiModelProperty(value = "数据库唯一主键ID | Unique primary key ID")
    private String id;

    /** 创建人 */
    @CreatedBy
    @Column(name = "created_by", nullable = true, updatable = false)
    @ApiModelProperty(value = "创建者 | Record created by")
    private String createdBy;

    /** 创建时间 */
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss", timezone = "GMT+8")
    @Column(name = "created_time", nullable = true, updatable = false)
    @ApiModelProperty(value = "创建时间 | Record created time")
    private Date createdTime;

    /** 更新人 */
    @LastModifiedBy
    @Column(name = "updated_by", nullable = true, insertable = false)
    @ApiModelProperty(value = "更新者 | Record updated by")
    private String updatedBy;

    /** 更新时间 */
    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss", timezone = "GMT+8")
    @Column(name = "updated_time", nullable = true, insertable = false)
    @ApiModelProperty(value = "更新时间 | Record updated time")
    private Date updatedTime;

    /** 乐观锁 */
    @Column(name = "revision")
    @ApiModelProperty(value = "版本戳 | Record revision")
    private Integer revision = 0;

    /** 封装 分页 页码 */
    @Transient
    @ApiModelProperty(value = "jpa分页开始页码(从0开始) | Record offset")
    public int offset;

    /** 封装 分页 条数 */
    @Transient
    @ApiModelProperty(value = "jpa分页每页展示条数(默认10条) | Record limit")
    public int limit = 10;

}
