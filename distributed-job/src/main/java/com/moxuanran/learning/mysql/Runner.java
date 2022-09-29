package com.moxuanran.learning.mysql;

import lombok.Data;

import java.util.Date;

/**
 * Runner
 *
 * @author wutao
 * @date 2022/09/29
 */
@Data
public class Runner {

    private int id;

    private String ip;

    private Date heartbeat;

}
