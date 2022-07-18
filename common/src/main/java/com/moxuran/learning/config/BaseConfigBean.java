package com.moxuran.learning.config;


/**
 * @author yjy
 * @date 2020/9/1 10:54
 **/
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
