package com.tlc.group.seven.marketdataservice.marketdata.controller;

import com.tlc.group.seven.marketdataservice.kafka.producer.KafkaProducer;
import com.tlc.group.seven.marketdataservice.log.model.LogData;
import com.tlc.group.seven.marketdataservice.marketdata.model.MarketData;
import com.tlc.group.seven.marketdataservice.marketdata.model.OrderData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Date;


@RestController
@RequestMapping("/api/v1")
public class MarketDataController {

	@Autowired
	private WebClient.Builder webClientBuilder;

	@Autowired
	private KafkaProducer kafkaProducer;

    @PostMapping("/webhook")
	public void latestOrder(@RequestBody OrderData data){
		System.out.println(data);
		LogData logData = new LogData("webhook", "latestOrder", "Webhook url updated", "market-data", new Date());
		kafkaProducer.sendResponseToKafkaLogData(logData);
	}

	@GetMapping("/pd")
	public MarketData[] getMarketData(){
		LogData logData = new LogData("market data", "getMarketData", "market data fetch from exchange", "market-data", new Date());
		kafkaProducer.sendResponseToKafkaLogData(logData);

		return webClientBuilder.build()
		.get()
		.uri("https://exchange.matraining.com/pd")
		.retrieve()
		.bodyToMono(MarketData[].class)
		.block();
	}
}
