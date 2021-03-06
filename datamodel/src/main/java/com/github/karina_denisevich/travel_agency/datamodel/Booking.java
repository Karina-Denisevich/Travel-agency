package com.github.karina_denisevich.travel_agency.datamodel;

import com.github.karina_denisevich.travel_agency.annotation.DbTable;

import java.util.Date;

@DbTable(name = "booking")
public class Booking extends AbstractModel {

    private static final long serialVersionUID = 1L;

    private Date orderDate;
    private Boolean isConfirmed;
    private User user;
    private Tour tour;

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Boolean getIsConfirmed() {
        return isConfirmed;
    }

    public void setIsConfirmed(Boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "orderDate=" + orderDate +
                ", isConfirmed=" + isConfirmed +
                '}';
    }
}
