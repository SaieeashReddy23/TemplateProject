package com.example.templateProject.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//@Service
@Slf4j
public class KafkaConsumer {


    @KafkaListener(topics = "my_topic" , groupId = "group_id")
    public void consume(String message){
        log.info("consumed message :  {} " , message );
    }
}
