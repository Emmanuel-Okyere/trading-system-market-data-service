package com.tlc.group.seven.marketdataservice.kafka.controller;

import com.tlc.group.seven.marketdataservice.kafka.producer.KafkaProducer;
import com.tlc.group.seven.marketdataservice.log.model.LogData;
import com.tlc.group.seven.marketdataservice.marketdata.model.MarketData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

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