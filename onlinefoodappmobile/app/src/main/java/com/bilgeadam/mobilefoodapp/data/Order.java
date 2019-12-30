package com.bilgeadam.mobilefoodapp.data;

import java.util.Date;

public class Order {
    private Long orderId;
    private Long price;
    private Date placementDate;
    private Boolean status;

    public Order(Long orderId, Long price, Date placementDate, Boolean status) {
        this.orderId = orderId;
        this.price = price;
        this.placementDate = placementDate;
        this.status = status;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Date getPlacementDate() {
        return placementDate;
    }

    public void setPlacementDate(Date placementDate) {
        this.placementDate = placementDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
