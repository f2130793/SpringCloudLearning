package com.moxuanran.learning.service;

import com.moxuanran.learning.entity.Order;
import com.moxuanran.learning.mapper.OrderDao;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author 莫轩然
 * @date 2020/7/21 16:11
 */
@Service
public class OrderService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private RestTemplate restTemplate;

    @GlobalTransactional(name = "test-create-order",rollbackFor = Exception.class)
    public void create(Order order) {
        System.out.println("--------->下单开始");
        orderDao.create(order);

        System.out.println("--------->扣库存开始");
        //扣库存
        restTemplate.getForObject(
                "http://seata-storage-service/storage/decrease?productId="
                        + order.getProductId() + "&count=" + order.getCount()
                , String.class);
        System.out.println("--------->扣库存结束");
        //扣余额
        System.out.println("--------->扣余额开始");
        restTemplate.getForObject("http://seata-account-service/account/decrease?userId=" + order.getUserId() +
                "&money=" + order.getMoney(), String.class);
        System.out.println("--------->扣余额结束");

        System.out.println("--------->下单结束");
    }
}
