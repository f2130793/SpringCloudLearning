package com.moxuanran.learning;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.KafkaListeners;


/**
 * @author 莫轩然
 * @date 2020/8/9 21:21
 */
@SpringBootApplication
public class KafkaApplication {
    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class, args);
    }

    @KafkaListeners(
            value = {
                    @KafkaListener(topics = {"topic01"},groupId = "group1")
            }
    )
    public void recevice01(ConsumerRecord<String,String> record){
        System.out.println("record:" + record);
    }
}
