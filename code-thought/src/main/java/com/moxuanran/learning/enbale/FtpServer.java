package com.moxuanran.learning.enbale;

import org.springframework.stereotype.Service;

/**
 * @author 莫轩然(wutao07)
 * @date 2022/9/25 下午7:34
 */
@Service
public class FtpServer implements Server{
    @Override
    public void start() {
        System.out.println("FTP 服务启动中......");
    }

    @Override
    public void stop() {
        System.out.println("FTP 服务停止中......");
    }
}
