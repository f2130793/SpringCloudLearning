package com.moxuanran.learning.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 莫轩然
 * @date 2020/7/21 15:59
 */
@Data
@TableName("account")
public class Account {
    private Integer id;
    private Integer userId;
    private Integer total;
    private Integer used;
    private Integer residue;
}
