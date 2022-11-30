package com.tlc.group.seven.marketdataservice;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Mono;


@RestController
public class MarketDataServiceController {

	@Autowired
	private WebClient.Builder webClientBuilder;

    @PostMapping("/api/latest/pd")
	public void latestOrder(@RequestBody OrderData data){	
		System.out.println(data);
	}

	@GetMapping("/market-data")
	public MarketData[] getMarketData(){

		return webClientBuilder.build()
		.get()
		.uri("https://exchange.matraining.com/pd")
		.retrieve()
		.bodyToMono(MarketData[].class)
		.block();
	}
}
