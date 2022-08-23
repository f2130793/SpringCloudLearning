package com.moxuanran.learning.controller;

import com.moxuanran.learning.service.SayHello;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 莫轩然
 * @date 2020/7/20 10:42
 */
@RestController
public class FallbackController {

    @Autowired
    private SayHello sayHello;

    @GetMapping("/fallback")
    public Object fallback() {
//        Map<String,Object> result = new HashMap<>();
//        result.put("data",null);
//        result.put("message","get request fallback");
//        result.put("code",500);

        return "get request fallback";
    }

    @GetMapping("/sayHello")
    public String sayHello(String name) {
        return sayHello.say(name);
    }
}
