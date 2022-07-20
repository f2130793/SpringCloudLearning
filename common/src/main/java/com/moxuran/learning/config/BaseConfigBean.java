package com.moxuran.learning.config;


/**
 * 基础配置bean
 *
 * @author wutao
 * @date 2022/07/20
 */
public class BaseConfigBean {

    /** 当前环境 */
    private String env;
    private String quickServer = "http://gate.idanchuang.com/quick-server";

    public String getQuickServer() {
        return quickServer;
    }

    public void setQuickServer(String quickServer) {
        this.quickServer = quickServer;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }
}
