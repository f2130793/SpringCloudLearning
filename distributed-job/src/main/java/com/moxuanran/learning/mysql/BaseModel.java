package com.moxuanran.learning.mysql;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wutao
 */
@Data
public abstract class BaseModel implements Serializable {

    /**
     * 主键ID
     */
    private long id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifiedTime;


}
