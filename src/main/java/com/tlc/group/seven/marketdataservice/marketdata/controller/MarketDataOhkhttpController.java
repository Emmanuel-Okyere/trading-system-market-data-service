package com.tlc.group.seven.marketdataservice.marketdata.controller;

import com.tlc.group.seven.marketdataservice.kafka.producer.KafkaProducer;
import com.tlc.group.seven.marketdataservice.log.model.LogData;
import com.tlc.group.seven.marketdataservice.marketdata.model.MarketData;
import com.tlc.group.seven.marketdataservice.marketdata.model.OrderData;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.Date;


@RestController
@RequestMapping("/api/v1")
public class MarketDataOhkhttpController {

	@Autowired
	private WebClient.Builder webClientBuilder;

	@Autowired
	private KafkaProducer kafkaProducer;

    @PostMapping("/webhook/new")
	public void latestOrder(@RequestBody OrderData data){
		System.out.println(data);
		LogData logData = new LogData("webhook", "latestOrder", "Webhook url triggered", "market-data", new Date());
		kafkaProducer.sendResponseToKafkaLogData(logData);
	}

	OkHttpClient client = new OkHttpClient();
	private final OkHttpClient httpClient = new OkHttpClient();

	public String run(String url) throws IOException {
		Request request = new Request.Builder().url(url).build();

		try (Response response = client.newCall(request).execute()) {
			response.sentRequestAtMillis();
			return response.body().string();
		}
	}


	public void getMarketData() throws IOException {
		LogData logData = new LogData("market data", "getMarketData", "market data fetch from exchange", "market-data", new Date());
		kafkaProducer.sendResponseToKafkaLogData(logData);


		String response = run("https://exchange.matraining.com/pd");
		System.out.println(response);

		System.out.printf("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa:: " + response + " status: " );

	}

	@GetMapping("/pd/new")
	private void sendGETSync() throws IOException {

		Request request = new Request.Builder()
				.url("https://exchange.matraining.com/md")
				.build();

		try (Response response = httpClient.newCall(request).execute()) {

			if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

			// Get response headers
			Headers responseHeaders = response.headers();
			for (int i = 0; i < responseHeaders.size(); i++) {
				System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
			}

			// Get response body
			System.out.println(response.body().string());
		}

	}
}
