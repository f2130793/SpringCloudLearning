package com.moxuanran.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 莫轩然
 * @date 2020/7/18 14:44
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GateWayDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(GateWayDemoApplication.class, args);
    }
}
