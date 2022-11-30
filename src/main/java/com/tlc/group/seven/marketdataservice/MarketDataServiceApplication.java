package com.tlc.group.seven.marketdataservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

=======
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
>>>>>>> 7fd7301bc4d1ef1696f1bc0a1518fbbfb99feb0a

@SpringBootApplication
@RestController
public class MarketDataServiceApplication {

	@Bean
	public WebClient.Builder getWebClientBuilder(){
		return WebClient.builder();
	}

	public static void main(String[] args) {
		SpringApplication.run(MarketDataServiceApplication.class, args);
	}
<<<<<<< HEAD
=======

	@PostMapping("/api/latest/pd")
	public void printData(@RequestBody OrderData data){	
		System.out.println(data);
	}


>>>>>>> 7fd7301bc4d1ef1696f1bc0a1518fbbfb99feb0a
}

