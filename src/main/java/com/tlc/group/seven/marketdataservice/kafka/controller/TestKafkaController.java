package com.tlc.group.seven.marketdataservice.kafka.controller;

import com.tlc.group.seven.marketdataservice.kafka.producer.KafkaProducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestKafkaController {
    @Autowired
    private KafkaProducer kafkaProducer;

    public TestKafkaController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }


    /*@PostMapping("/api/v1/kafka/test")
    public ResponseEntity<List<MarketData>> testKafka(@RequestBody List<MarketData> data){
        kafkaProducer.sendResponseToKafkaMarketData(data);
        LogData logData = new LogData("auth-login-2", "click", "creating user account", "market-data", new Date());
        kafkaProducer.sendResponseToKafkaLogData(logData);
        return ResponseEntity.ok("Data sent to Kafka...");
    }
    */
}