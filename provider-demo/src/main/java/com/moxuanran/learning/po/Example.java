package com.moxuanran.learning.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zoi7.component.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


/**
 * 例子
 *
 * @author wutao
 * @date 2022/07/18
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("example")
public class Example extends BaseEntity {

    @TableId
    private Long id;

    private String name;

    private Integer sex;

    private Date birthday;

}
