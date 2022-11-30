package com.tlc.group.seven.marketdataservice;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;


@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class MarketData{
    
    @JsonProperty
    private Double MAX_PRICE_SHIFT;
    @JsonProperty
    private Double LAST_TRADED_PRICE;
    @JsonProperty
    private Double BID_PRICE;
    @JsonProperty
    private String TICKER;
    @JsonProperty
    private Long SELL_LIMIT;
    @JsonProperty
    private Double ASK_PRICE;
    @JsonProperty
    private Long BUY_LIMIT;
}
