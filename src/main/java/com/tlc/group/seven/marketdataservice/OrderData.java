package com.tlc.group.seven.marketdataservice;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

<<<<<<< HEAD
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
=======
@JsonIgnoreProperties(ignoreUnknown = true)
>>>>>>> 7fd7301bc4d1ef1696f1bc0a1518fbbfb99feb0a
public class OrderData {

    public OrderData(){ 
    }
<<<<<<< HEAD
=======
    
>>>>>>> 7fd7301bc4d1ef1696f1bc0a1518fbbfb99feb0a
    private String orderType;
    private String product;
    private String side;
    private String orderID;
    private Integer qty;
    private Integer cumQty;
    private Double cumPrx;
    private String exchange;
    private String timestamp;
<<<<<<< HEAD
=======

    public String getOrderType() {
        return orderType;
    }
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
    public String getSide() {
        return side;
    }
    public void setSide(String side) {
        this.side = side;
    }
    public String getOrderID() {
        return orderID;
    }
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }
    public Integer getQty() {
        return qty;
    }
    public void setQty(Integer qty) {
        this.qty = qty;
    }
    public Integer getCumQty() {
        return cumQty;
    }
    public void setCumQty(Integer cumQty) {
        this.cumQty = cumQty;
    }
    public Double getCumPrx() {
        return cumPrx;
    }
    public void setCumPrx(Double cumPrx) {
        this.cumPrx = cumPrx;
    }
    public String getExchange() {
        return exchange;
    }
    public void setExchange(String exchange) {
        this.exchange = exchange;
    }
    
    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getProduct() {
        return product;
    }
    public void setProduct(String product) {
        this.product = product;
    } 

    @Override
    public String toString() {
        return "OrderData [orderType=" + orderType + ", product=" + product + ", side=" + side + ", orderID=" + orderID
                + ", qty=" + qty + ", cumQty=" + cumQty + ", cumPrx=" + cumPrx + ", exchange=" + exchange
                + ", timestamp=" + timestamp + "]";
    }
>>>>>>> 7fd7301bc4d1ef1696f1bc0a1518fbbfb99feb0a
}
