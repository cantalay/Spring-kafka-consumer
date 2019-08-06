package com.example.realtimedata;

import com.example.realtimedata.repository.LogDbRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RealtimeDataApplication {


    public static void main(String[] args) {
        SpringApplication.run(RealtimeDataApplication.class, args);
    }
    @Bean
    public KafkaConsumerListener messageListener() {
        return new KafkaConsumerListener();
    }
}
