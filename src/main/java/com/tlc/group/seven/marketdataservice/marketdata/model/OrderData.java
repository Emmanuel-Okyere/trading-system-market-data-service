package com.tlc.group.seven.marketdataservice.marketdata.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class OrderData {

    public OrderData(){ 
    }
    private String orderType;
    private String product;
    private String side;
    private String orderID;
    private Integer qty;
    private Integer cumQty;
    private Double cumPrx;
    private String exchange;
    private String timestamp;
}
