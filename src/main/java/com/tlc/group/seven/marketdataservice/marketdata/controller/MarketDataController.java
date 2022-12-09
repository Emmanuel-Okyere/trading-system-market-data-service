package com.tlc.group.seven.marketdataservice.marketdata.controller;

import com.tlc.group.seven.marketdataservice.constant.AppConstant;
import com.tlc.group.seven.marketdataservice.kafka.producer.KafkaProducer;
import com.tlc.group.seven.marketdataservice.marketdata.model.OrderData;
import com.tlc.group.seven.marketdataservice.marketdata.service.MarketDataService;
import com.tlc.group.seven.marketdataservice.marketdata.service.WebhookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
public class MarketDataController {

	@Autowired
	private KafkaProducer kafkaProducer;

	@Autowired
	private MarketDataService marketDataService;

	@Autowired
	private WebhookService webhookService;

    @PostMapping("/webhook/{exchange}")	
	public OrderData latestOrder(@PathVariable String exchange, @RequestBody OrderData data){
		return webhookService.webhook(exchange, data);
	}

	public void getMarketDataOnAppStart(){
		kafkaProducer.sendResponseToKafkaMarketData(marketDataService.getMarketData(AppConstant.baseUrlExchangeOne + AppConstant.marketData));
	}
}

