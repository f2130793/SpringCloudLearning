package com.moxuanran.learning.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 莫轩然
 * @date 2020/7/15 11:08
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Value("${server.port}")
    String port;

    @GetMapping("/say")
    public String say(String str){
        return str + "请求的port是：" + port;
    }

    @GetMapping("/say1/{id}")
    public String say1(@PathVariable Integer id){
        return id + "请求的port是：" + port;
    }
}