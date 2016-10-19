package com.github.karina_denisevich.travel_agency.datamodel;

import java.util.Date;

public class User extends AbstractModel{

    private String firstName;
    private String lastName;
    private String email;
    private Date bDay;
    private Double discount;
    private Integer ordersAmount;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getbDay() {
        return bDay;
    }

    public void setbDay(Date bDay) {
        this.bDay = bDay;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getOrdersAmount() {
        return ordersAmount;
    }

    public void setOrdersAmount(Integer ordersAmount) {
        this.ordersAmount = ordersAmount;
    }
}
