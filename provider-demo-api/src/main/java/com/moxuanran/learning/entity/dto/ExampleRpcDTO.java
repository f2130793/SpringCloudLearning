package com.moxuanran.learning.entity.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author 
 * @date 2019/12/16 14:37
 **/
@Data
public class ExampleRpcDTO {

    private Long id;

    private String name;

    private Integer sex;

    private Date birthday;

}
