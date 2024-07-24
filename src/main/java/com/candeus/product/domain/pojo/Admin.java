package com.candeus.product.domain.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("administrator")
public class Admin {
    private static final long serialVersionUID = 1L;

    @TableId(value = "admin_id", type = IdType.AUTO)
    private String adminId;

    @TableField("name")
    private String name;

    @TableField("password")
    private String password;

    @TableField("avatar")
    private String avatar;

    @TableField("emergency_contact")
    private String emergencyContact;

    @TableField("admin_level")
    private Integer adminLevel;


    @TableField("create_time")
    //TODO 对时间进行格式化，因为如果直接返回LocalDateTimeL类型，直接这样返回的格式是这样的"createTime": "2024-07-11T15:45:45
    //TODO 如果要返回json格式，需要手动进行格式化，或者使用@JsonFormat注解
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @TableField("is_deleted")
    private Integer isDeleted;
}
