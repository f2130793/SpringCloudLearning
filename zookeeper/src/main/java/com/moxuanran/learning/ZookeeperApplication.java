package com.moxuanran.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author 莫轩然
 * @date 2020/7/29 16:54
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ZookeeperApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZookeeperApplication.class, args);
    }
}
