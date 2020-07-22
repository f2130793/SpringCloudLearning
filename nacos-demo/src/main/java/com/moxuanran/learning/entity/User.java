package com.moxuanran.learning.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author 莫轩然
 * @date 2020/7/17 09:49
 */
@Data
@TableName(value = "users")
public class User {
    private Integer id;

    private String name;

    private String truename;

    private String email;

    private String mobile;

    private String password;

    private String officeArea;

    private String saleTel;

    private String erpUuid;

    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;

    private Date lastLoginAt;

    private String verificationToken;

    private Date verificationTokenExpire;

    private String state;

    private static final long serialVersionUID = 1L;
}
