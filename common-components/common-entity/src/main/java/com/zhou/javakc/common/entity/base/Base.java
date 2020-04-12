package com.zhou.javakc.common.entity.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zhou.javakc.common.entity.listener.CustomUserListener;
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
import java.sql.Timestamp;
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
    /**
     * 数据库主键ID
     * 采用hibernate的uuid生成32位字符串
     */
    @Id
    @Column(name = "id", length = 32)
    @GeneratedValue(generator = "generator_uuid")
    @GenericGenerator(name = "generator_uuid", strategy = "uuid")
    private String id;

    /** 创建人 */
    @CreatedBy
    @Column(name = "create_by", nullable = true, updatable = false)
    private String createdBy;

    /** 创建时间 */
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Column(name = "create_time", nullable = true, updatable = false)
    private Timestamp createdTime;

    /** 更新人 */
    @LastModifiedBy
    @Column(name = "update_by", nullable = true, insertable = false)
    private String updatedBy;

    /** 更新时间 */
    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Column(name = "update_time", nullable = true, insertable = false)
    private Timestamp updatedTime;

    /** 乐观锁 */
    @Column(name = "revision")
    private Integer revision = 0;

    /** ------------------------------分页查询条件------------------------------ */

    /** 封装 分页 页码 */
//    @JsonIgnore
    @Transient
    public int offset;

    /** 封装 分页 条数 */
//    @JsonIgnore
    @Transient
    public int limit = 10;

    /** ------------------------------动态查询条件------------------------------ */
    /** 数字区间 开始 参数 */
    @JsonIgnore
    @Transient
    public Long snumber;

    /** 数字区间 结束 参数 */
    @JsonIgnore
    @Transient
    public Long enumber;

    /** 日期区间 开始 参数 */
    @Transient
    @JsonIgnore
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    public Date sdate;

    /** 日期区间 结束 参数 */
    @Transient
    @JsonIgnore
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    public Date edate;

}
