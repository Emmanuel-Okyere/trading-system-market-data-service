package com.tlc.group.seven.marketdataservice.log.system.service;

import com.tlc.group.seven.marketdataservice.constant.AppConstant;
import com.tlc.group.seven.marketdataservice.kafka.producer.KafkaProducer;
import com.tlc.group.seven.marketdataservice.log.system.model.SystemLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemLogService {

    @Autowired
    KafkaProducer kafkaProducer;

    public void sendSystemLogToReportingService(String title, String event, String description){
        SystemLog systemLog = new SystemLog(title, event, description, AppConstant.microserviceServiceName);
        kafkaProducer.sendResponseToKafkaLogData(systemLog);
    }
}
