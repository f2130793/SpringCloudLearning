package com.moxuanran.learning.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 莫轩然
 * @date 2020/7/21 15:02
 */
@Data
@TableName(value = "storage")
public class Storage {
    private Integer id;
    private Integer productId;
    private Integer total;
    private Integer used;
    private Integer residue;
}
