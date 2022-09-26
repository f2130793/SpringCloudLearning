package com.moxuanran.learning.enbale;

/**
 * @author 莫轩然(wutao07)
 * @date 2022/9/25 下午7:33
 */
public interface Server {
    /**
     * 开始
     */
    void start();

    /**
     * 停止
     */
    void stop();

    enum Type {
        /**
         * http
         */
        HTTP,
        /**
         * ftp
         */
        FTP
    }

}
