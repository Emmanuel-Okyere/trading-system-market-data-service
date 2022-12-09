package com.tlc.group.seven.marketdataservice.marketdata.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.tlc.group.seven.marketdataservice.constant.AppConstant;
import com.tlc.group.seven.marketdataservice.kafka.producer.KafkaProducer;
import com.tlc.group.seven.marketdataservice.log.system.service.SystemLogService;
import com.tlc.group.seven.marketdataservice.marketdata.model.MarketData;
import com.tlc.group.seven.marketdataservice.marketdata.model.OrderData;

public class WebhookService {

    @Autowired
	MarketDataService marketDataService;

    @Autowired
	private KafkaProducer kafkaProducer;

    @Autowired
	SystemLogService systemLogService;

    public OrderData webhook(String exchange, OrderData data){
        List<MarketData> latestMarketData = null;
		if (exchange.equals("exchange1")){
			latestMarketData = marketDataService.getMarketData(AppConstant.baseUrlExchangeOne +AppConstant.marketData);
		}
		else if (exchange.equals("exchange2")){
			latestMarketData = marketDataService.getMarketData(AppConstant.baseUrlExchangeTwo +AppConstant.marketData);
		}
		if (latestMarketData != null){
			kafkaProducer.sendResponseToKafkaMarketData(latestMarketData);
		}
		systemLogService.sendSystemLogToReportingService("webhook", AppConstant.systemTriggeredEvent, "Webhook url triggered");

        return data;
    }
    
}
