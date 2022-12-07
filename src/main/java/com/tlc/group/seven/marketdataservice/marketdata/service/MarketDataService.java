package com.tlc.group.seven.marketdataservice.marketdata.service;

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

    public List<MarketData> getMarketData(){
        return List.of(Objects.requireNonNull(webClientBuilder.build()
                .get()
                .uri("https://exchange.matraining.com/pd")
                .retrieve()
                .bodyToMono(MarketData[].class)
                .block()));
    }
}
