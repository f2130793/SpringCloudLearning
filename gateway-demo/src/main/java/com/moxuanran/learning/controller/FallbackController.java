package com.moxuanran.learning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 莫轩然
 * @date 2020/7/20 10:42
 */
@RestController
public class FallbackController {
    @GetMapping("/fallback")
    public Object fallback(){
//        Map<String,Object> result = new HashMap<>();
//        result.put("data",null);
//        result.put("message","get request fallback");
//        result.put("code",500);

        return "get request fallback";
    }
}
