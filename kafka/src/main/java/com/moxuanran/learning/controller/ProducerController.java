package com.moxuanran.learning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 莫轩然
 * @date 2020/8/13 09:30
 */
@RestController
public class ProducerController {
    @Autowired
    KafkaTemplate<String,Object> kafkaTemplate;

    @RequestMapping("/message/send")
    @Transactional(rollbackFor = Exception.class)
    public void send(String msg){
        kafkaTemplate.send("topic01",msg);
    }
}
