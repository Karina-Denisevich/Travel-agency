package com.github.karina_denisevich.travel_agency.datamodel;

import java.util.Date;

public class Order extends AbstractModel {

    private Date orderDate;
    private Boolean isConfirmed;
    private Long userId;
    private Long tourId;

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Boolean getConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        isConfirmed = confirmed;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTourId() {
        return tourId;
    }

    public void setTourId(Long tourId) {
        this.tourId = tourId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderDate=" + orderDate +
                ", isConfirmed=" + isConfirmed +
                ", userId=" + userId +
                ", tourId=" + tourId +
                '}';
    }
}
