package com.github.karina_denisevich.travel_agency.datamodel;

import com.github.karina_denisevich.travel_agency.annotation.DbTable;

import java.util.Date;

@DbTable(name = "user_details")
public class UserDetails extends AbstractModel {

    private String firstName;
    private String lastName;
    private Double discount;
    private Date bDate;
    private String phone;
    private String skype;

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

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Date getbDate() {
        return bDate;
    }

    public void setbDate(Date bDate) {
        this.bDate = bDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", discount=" + discount +
                ", bDate=" + bDate +
                ", phone='" + phone + '\'' +
                ", skype='" + skype + '\'' +
                '}';
    }
}
