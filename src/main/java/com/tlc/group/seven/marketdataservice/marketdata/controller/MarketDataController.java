package com.tlc.group.seven.marketdataservice.marketdata.controller;

import com.tlc.group.seven.marketdataservice.marketdata.model.MarketData;
import com.tlc.group.seven.marketdataservice.marketdata.model.OrderData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;


@RestController
@RequestMapping("/api/v1")
public class MarketDataController {

	@Autowired
	private WebClient.Builder webClientBuilder;

    @PostMapping("/webhook")
	public void latestOrder(@RequestBody OrderData data){
		System.out.println(data);
	}

	@GetMapping("/pd")
	public MarketData[] getMarketData(){

		return webClientBuilder.build()
		.get()
		.uri("https://exchange.matraining.com/pd")
		.retrieve()
		.bodyToMono(MarketData[].class)
		.block();
	}
}
