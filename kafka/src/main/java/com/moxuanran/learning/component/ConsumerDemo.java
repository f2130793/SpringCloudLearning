package com.moxuanran.learning.component;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author 莫轩然
 * @date 2020/8/13 09:33
 */
@Component
public class ConsumerDemo {
    @KafkaListener(topics = "topic01",groupId = "group2")
    public void listen(ConsumerRecord<?,?> record){
        System.out.printf("topic is %s ,offset is %d,value is %s \n",record.topic(),record.offset(),record.value());
    }
}
