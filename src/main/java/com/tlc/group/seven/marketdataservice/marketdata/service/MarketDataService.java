package com.tlc.group.seven.marketdataservice.marketdata.service;

import com.tlc.group.seven.marketdataservice.constant.AppConstant;
import com.tlc.group.seven.marketdataservice.log.system.service.SystemLogService;
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

    @Autowired
    private SystemLogService systemLogService;

    public List<MarketData> getMarketData(String endpoint){
        systemLogService.sendSystemLogToReportingService("getMarketData", AppConstant.systemTriggeredEvent, "get market data from exchange");
        return List.of(Objects.requireNonNull(webClientBuilder.build()
                .get()
                .uri(endpoint)
                .retrieve()
                .bodyToMono(MarketData[].class)
                .block()));
    }
}
