package com.tlc.group.seven.marketdataservice.socket.controller;

import com.tlc.group.seven.marketdataservice.constant.AppConstant;
import com.tlc.group.seven.marketdataservice.marketdata.model.MarketData;
import com.tlc.group.seven.marketdataservice.marketdata.service.MarketDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@CrossOrigin(origins = "*")
@Controller
public class MarketDataWebSocket {

    @Autowired
    private MarketDataService marketDataService;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String greeting(MarketData marketData) throws Exception {
        Thread.sleep(1000);
        List<MarketData>  data = marketDataService.getMarketData(AppConstant.baseUrlExchangeOne + AppConstant.marketData);
        System.out.println("Socket" + data);
        return "Hello, " + HtmlUtils.htmlEscape(marketData.toString()) + "!";
    }
}


