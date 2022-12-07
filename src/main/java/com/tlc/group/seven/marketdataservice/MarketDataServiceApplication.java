package com.tlc.group.seven.marketdataservice;

import com.tlc.group.seven.marketdataservice.marketdata.controller.MarketDataController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;


@SpringBootApplication
public class MarketDataServiceApplication implements CommandLineRunner {

	public MarketDataServiceApplication(MarketDataController marketDataController) {
		this.marketDataController = marketDataController;
	}



	public static void main(String[] args) {
		SpringApplication.run(MarketDataServiceApplication.class, args);
	}

	@Autowired
	private final MarketDataController marketDataController;
	@Override
	public void run(String... args) throws Exception{
		marketDataController.getMarketDataFirst();
		System.out.println("********************APP START*******************");
	}
}

