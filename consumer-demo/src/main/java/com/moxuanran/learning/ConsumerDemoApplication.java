package com.moxuanran.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 莫轩然
 * @date 2020/7/15 11:16
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients(basePackages = "com.moxuanran")
@EnableSwagger2
public class ConsumerDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerDemoApplication.class, args);
    }
}
