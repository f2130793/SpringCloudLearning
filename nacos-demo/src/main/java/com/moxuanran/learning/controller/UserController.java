package com.moxuanran.learning.controller;

import com.moxuanran.learning.entity.User;
import com.moxuanran.learning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 莫轩然
 * @date 2020/7/17 09:55
 */
@RestController
@RefreshScope
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/getUserList")
    public Integer getUserList(){
        List<User> list = userService.list();

        return list.size();
    }
}
