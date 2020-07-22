package com.moxuanran.learning.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author 莫轩然
 * @date 2020/7/21 15:35
 */
@Data
@TableName("order")
public class Order {
    private Integer id;
    private Integer userId;
    private Integer productId;
    private Integer count;
    private BigDecimal money;
    private Integer status;
}
