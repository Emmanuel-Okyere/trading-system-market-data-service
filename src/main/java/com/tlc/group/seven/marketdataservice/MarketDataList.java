package com.tlc.group.seven.marketdataservice;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class MarketDataList {
    private List<MarketData> marketDatas;
    
    public MarketDataList(){
        marketDatas = new ArrayList<>();
    }
    
}
