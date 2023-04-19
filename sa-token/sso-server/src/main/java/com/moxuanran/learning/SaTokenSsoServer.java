package com.moxuanran.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wutao
 * @date 2023/4/17 10:50
 */
@SpringBootApplication
public class SaTokenSsoServer {
    public static void main(String[] args) {
        SpringApplication.run(SaTokenSsoServer.class, args);
        System.out.println("\n---- Sa-Token-SSO服务启动成功");
    }
}
