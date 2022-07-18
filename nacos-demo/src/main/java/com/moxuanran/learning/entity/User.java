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
    private Long id;

    private String userName;

    private String nickName;

    private Integer age;

    private String userSex;

    private static final long serialVersionUID = 1L;
}
