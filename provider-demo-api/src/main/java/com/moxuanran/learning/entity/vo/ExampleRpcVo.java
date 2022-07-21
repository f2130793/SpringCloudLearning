package com.moxuanran.learning.entity.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author yjy
 * @date 2019/12/16 14:42
 **/
@Data
public class ExampleRpcVo {

    private Long id;

    private String name;

    private Integer sex;

    private Date birthday;
}
