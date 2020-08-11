package com.moxuanran.learning;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.KafkaListeners;

import java.io.IOException;

/**
 * @author 莫轩然
 * @date 2020/8/9 21:21
 */
@SpringBootApplication
public class KafkaApplication {
    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class, args);
        try {
            int read = System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @KafkaListeners(
            value = {
                    @KafkaListener(topics = {"topic01"})
            }
    )
    public void recevice01(ConsumerRecord<String,String> record){
        System.out.println("record:" + record);
    }
}
