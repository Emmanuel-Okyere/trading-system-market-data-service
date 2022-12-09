package com.tlc.group.seven.marketdataservice.marketdata.controller;

import com.tlc.group.seven.marketdataservice.constant.AppConstant;
import com.tlc.group.seven.marketdataservice.kafka.producer.KafkaProducer;
import com.tlc.group.seven.marketdataservice.log.system.service.SystemLogService;
import com.tlc.group.seven.marketdataservice.marketdata.model.MarketData;
import com.tlc.group.seven.marketdataservice.marketdata.model.OrderData;
import com.tlc.group.seven.marketdataservice.marketdata.service.MarketDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/api/v1")
public class MarketDataController {

	@Autowired
	private WebClient.Builder webClientBuilder;

	@Autowired
	private KafkaProducer kafkaProducer;

	@Autowired
	MarketDataService marketDataService;

	@Autowired
	SystemLogService systemLogService;


    @PostMapping("/webhook/{exchange}")	
	public void latestOrder(@PathVariable String exchange, @RequestBody OrderData data){
		List<MarketData> latestMarketData = null;
		System.out.println(data);
		if (exchange.equals("exchange1")){
			latestMarketData = getMarketData( AppConstant.baseUrlExchangeOne + AppConstant.marketData);
		}
		else if (exchange.equals("exchange2")){
			latestMarketData = getMarketData(AppConstant.baseUrlExchangeOne + AppConstant.marketData);
		}

		if (latestMarketData != null){
			kafkaProducer.sendResponseToKafkaMarketData(latestMarketData);
		}
		systemLogService.sendSystemLogToReportingService("webhook", AppConstant.systemTriggeredEvent, "Webhook url triggered");
	}

	private List<MarketData> getMarketData(String exchange){
		systemLogService.sendSystemLogToReportingService("market data", "getMarketData", "market data fetch from exchange");

		List<MarketData> latestMarketData = marketDataService.getMarketData(exchange);

		kafkaProducer.sendResponseToKafkaMarketData(latestMarketData);
		return latestMarketData;
	}

	public void getMarketDataOnAppStart(){
		kafkaProducer.sendResponseToKafkaMarketData(marketDataService.getMarketData(AppConstant.baseUrlExchangeOne + AppConstant.marketData));
	}
}

