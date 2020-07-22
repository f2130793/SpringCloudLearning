package com.moxuanran.learning.controller;

import com.moxuanran.learning.service.HystrixTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author 莫轩然
 * @date 2020/7/16 11:05
 */
@RestController
@RequestMapping("/hystrix")
public class HystrixController {
    @Autowired
    HystrixTestService hystrixTestService;

    @GetMapping("/get")
    public String get(){
        return hystrixTestService.get("ssds");
    }

    @GetMapping("/getCache")
    public String getCache(){
        return hystrixTestService.getCache("333eee");
    }
}
