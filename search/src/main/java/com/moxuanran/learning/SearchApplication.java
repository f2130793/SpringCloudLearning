package com.moxuanran.learning;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 莫轩然
 * @date 2020/6/28 14:19
 */
@SpringBootApplication
@MapperScan(basePackages = "com.moxuanran.learning.mapper")
public class SearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class, args);
    }
}
