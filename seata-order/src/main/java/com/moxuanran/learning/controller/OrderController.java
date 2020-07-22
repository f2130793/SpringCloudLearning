package com.moxuanran.learning.controller;

import com.moxuanran.learning.entity.Order;
import com.moxuanran.learning.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 莫轩然
 * @date 2020/7/21 16:13
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/create")
    public String create(Order order){
        orderService.create(order);

        return "订单创建成功";
    }
}
