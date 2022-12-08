package com.tlc.group.seven.marketdataservice.marketdata.service;

import com.tlc.group.seven.marketdataservice.kafka.producer.KafkaProducer;
import com.tlc.group.seven.marketdataservice.log.model.SystemLog;
import com.tlc.group.seven.marketdataservice.marketdata.model.MarketData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Objects;

@Service
public class MarketDataService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private KafkaProducer kafkaProducer;

    public List<MarketData> getMarketData(){
        SystemLog systemLog = new SystemLog("Get Market Data", "getMarketData", "get market data from exchange", "market-data");
        kafkaProducer.sendResponseToKafkaLogData(systemLog);
        return List.of(Objects.requireNonNull(webClientBuilder.build()
                .get()
                .uri("https://exchange.matraining.com/pd")
                .retrieve()
                .bodyToMono(MarketData[].class)
                .block()));
    }
}
