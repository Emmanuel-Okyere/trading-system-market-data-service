package com.tlc.group.seven.marketdataservice.controller;

import com.tlc.group.seven.marketdataservice.config.kafka.producer.KafkaProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestKafkaController {
    private KafkaProducer kafkaProducer;

    public TestKafkaController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/api/v1/kafka/test")
    public ResponseEntity<String> testKafka(@RequestBody String data){
        kafkaProducer.sendResponseToKafka(data);
        return ResponseEntity.ok("Data sent to Kafka...");
    }
}