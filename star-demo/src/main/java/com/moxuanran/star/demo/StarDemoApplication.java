package com.moxuanran.star.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wutao
 * @date 2022/10/20 10:27
 */
@SpringBootApplication
@EnableFeignClients("com.netease")
public class StarDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(StarDemoApplication.class, args);
    }
}
