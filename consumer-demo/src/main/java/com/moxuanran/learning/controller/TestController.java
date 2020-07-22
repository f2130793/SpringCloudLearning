package com.moxuanran.learning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


/**
 * @author 莫轩然
 * @date 2020/7/15 11:25
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    LoadBalancerClient lb;

    @RequestMapping("/get")
    public String get() {
        ServiceInstance instance = lb.choose("provider-demo-server");

        String url = "http://" + instance.getServiceId() + "/test/say?str=hshhsh";
        //String url1 = "http://" + instance.getHost()+":" + instance.getPort() + "/test/say?str=hshhsh";

        return  restTemplate.getForObject(url, String.class);
    }

    @RequestMapping("/get1")
    public String get1() {
        String url = "lb://provider-demo-server" + "/test/say?str=hshhsh";

        return  restTemplate.getForObject(url, String.class);
    }
}
