package com.example.realtimedata;

import com.example.realtimedata.model.Log4Db;
import com.example.realtimedata.repository.LogDAL;
import com.example.realtimedata.repository.LogDbRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.List;

public class KafkaConsumerListener {


    @Value(value = "${websocket.messages.in}")
    private String destinationMessagesIn;

    @Value(value = "${websocket.messages.out}")
    private String destinationMessagesOut;

    @Autowired
    private SimpMessagingTemplate template;



    @KafkaListener(topics = "${kafka.topic.in}", containerFactory = "rawKafkaListenerContainerFactory")
    public void listenTopicIn(ConsumerRecord<String, String> cr) throws Exception {

        template.convertAndSend(destinationMessagesIn, cr.key() + " " + cr.value());
    }

    @KafkaListener(topics = "${kafka.topic.out}", containerFactory = "rawKafkaListenerContainerFactory")
    public void listenTopicOut(ConsumerRecord<String, String> cr) throws Exception {

        template.convertAndSend(destinationMessagesOut, cr.key() + " " + cr.value());

        System.out.println("DENEMEBIRKI : " + cr.key().toString());
        System.out.println("DENEMEUCDORT : " + cr.value().toString());
    }



}
