package com.moxuanran.learning;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 莫轩然
 * @date 2020/7/17 09:19
 */
@SpringBootApplication
@MapperScan(basePackages = "com.moxuanran.learning.mapper")
public class NacosDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosDemoApplication.class, args);
    }
}
