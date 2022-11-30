package com.tlc.group.seven.marketdataservice.controller;

import com.tlc.group.seven.marketdataservice.config.kafka.producer.KafkaProducer;
import com.tlc.group.seven.marketdataservice.model.LogData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class TestKafkaController {
    private KafkaProducer kafkaProducer;

    public TestKafkaController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/api/v1/kafka/test")
    public ResponseEntity<String> testKafka(@RequestBody String data){
        kafkaProducer.sendResponseToKafkaMarketData(data);
        LogData logData = new LogData("auth-login-2", "click", "creating user account", "market-data", new Date());
        kafkaProducer.sendResponseToKafkaLogData(logData);
        return ResponseEntity.ok("Data sent to Kafka...");
    }
}