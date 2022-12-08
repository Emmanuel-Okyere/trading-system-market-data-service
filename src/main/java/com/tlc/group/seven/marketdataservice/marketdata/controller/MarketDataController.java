package com.tlc.group.seven.marketdataservice.marketdata.controller;

import com.tlc.group.seven.marketdataservice.kafka.producer.KafkaProducer;
import com.tlc.group.seven.marketdataservice.log.model.SystemLog;
import com.tlc.group.seven.marketdataservice.marketdata.model.MarketData;
import com.tlc.group.seven.marketdataservice.marketdata.model.OrderData;
import com.tlc.group.seven.marketdataservice.marketdata.service.MarketDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Date;
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

    @PostMapping("/webhook/{exchange}")	
	public void latestOrder(@PathVariable String exchange, @RequestBody OrderData data){
		System.out.println(exchange+ " outside get market");
		List<MarketData> latestMarketData = null;
		System.out.println(data);
		if (exchange.equals("exchange1")){
			System.out.println("exchange1");
			latestMarketData = getMarketData("https://exchange.matraining.com/pd");
		}
		else if (exchange.equals("exchange2")){
			System.out.println("exchange1");
			latestMarketData = getMarketData("https://exchange2.matraining.com/pd");
		}

		if (latestMarketData != null){
			System.out.println("kafka sent");
			kafkaProducer.sendResponseToKafkaMarketData(latestMarketData);
		}
		
		System.out.println("after all if statements");
		SystemLog systemLog = new SystemLog("webhook", "latestOrder", "Webhook url triggered", "market-data");
		kafkaProducer.sendResponseToKafkaLogData(systemLog);
	}


	private List<MarketData> getMarketData(String exchange){
		System.out.println("Callled market");
		SystemLog systemLog = new SystemLog("market data", "getMarketData", "market data fetch from exchange", "market-data");

		kafkaProducer.sendResponseToKafkaLogData(systemLog);

		List<MarketData> latestMarketData = List.of(Objects.requireNonNull(webClientBuilder.build()
				.get()
				.uri(exchange)
				.retrieve()
				.bodyToMono(MarketData[].class)
				.block()));

		kafkaProducer.sendResponseToKafkaMarketData(latestMarketData);
		return latestMarketData;
	}

	public void getMarketDataOnAppStart(){
		kafkaProducer.sendResponseToKafkaMarketData(marketDataService.getMarketData());
	}


}

