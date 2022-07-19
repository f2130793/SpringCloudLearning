package com.moxuanran.learning.controller;

import com.moxuanran.learning.ExampleClient;
import com.moxuran.learning.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Autowired
    ExampleClient exampleClient;

    @RequestMapping("/get")
    public String get() {
        ServiceInstance instance = lb.choose("provider-demo");

        String url = "http://" + instance.getServiceId() + "/test/say?str=hshhsh";
        //String url1 = "http://" + instance.getHost()+":" + instance.getPort() + "/test/say?str=hshhsh";

        return  restTemplate.getForObject(url, String.class);
    }

    @RequestMapping("/get1")
    public String get1() {
        String url = "lb://provider-demo" + "/test/say?str=hshhsh";

        return  restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/get2")
    public JsonResult<String> get2(@RequestParam("msg") String msg) {
       return exampleClient.hello(msg).mustSuccess();
    }
}
