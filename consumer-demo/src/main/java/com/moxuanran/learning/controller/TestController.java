package com.moxuanran.learning.controller;

import com.moxuanran.learning.ExampleClient;
import com.moxuran.learning.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author 莫轩然
 * @date 2020/7/15 11:25
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    ExampleClient exampleClient;

    @GetMapping("/get2")
    public JsonResult<String> get2(@RequestParam("msg") String msg) {
       return exampleClient.hello(msg).mustSuccess();
    }
}
