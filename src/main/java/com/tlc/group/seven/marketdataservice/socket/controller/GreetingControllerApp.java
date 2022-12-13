package com.tlc.group.seven.marketdataservice.socket.controller;


import com.tlc.group.seven.marketdataservice.constant.AppConstant;
import com.tlc.group.seven.marketdataservice.marketdata.model.MarketData;
import com.tlc.group.seven.marketdataservice.marketdata.service.MarketDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.List;

record GreetingRequest(String name) {
}

record GreetingResponse(String message) {
}

@Controller
class GreetingControllerApp {

    @Autowired
    private MarketDataService marketDataService;

    @MessageExceptionHandler
    @SendTo("/topic/errors")
    public String handleException(IllegalArgumentException e) {
        var message = ("an exception occurred! " + NestedExceptionUtils.getMostSpecificCause(e));
        System.out.println(message);
        return message;
    }

    @MessageMapping("/chat")
    @SendTo("/topic/greetings")
    GreetingResponse greet(GreetingRequest request) throws Exception {
        List<MarketData> data = marketDataService.getMarketData(AppConstant.baseUrlExchangeOne + AppConstant.marketData);
        System.out.println("Socket" + data);
        Assert.isTrue(Character.isUpperCase(request.name().charAt(0)), () -> "the name must start with a capital letter!");
        Thread.sleep(1_000);
        return new GreetingResponse("Hello, " + request.name());
    }


}

@Configuration
@EnableWebSocketMessageBroker
class GreetingWebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat").withSockJS();
    }
}