package com.moxuanran.learning.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "ims_autoparts_car_brand")
public class CarSerialBrand {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer uniacid;
    private String initials;
    private Integer parent_id;
    private String name;
    private String pic_url;
    private Integer status;
    private Integer sort;
    private Integer is_hot;
    private Integer create_time;
}
