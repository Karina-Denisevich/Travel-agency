package com.github.karina_denisevich.travel_agency.web.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class BookingDto extends AbstractDto {

    @NotEmpty(message = "Date should not be empty")
    private String orderDate;
    private Boolean isConfirmed;
    @NotNull(message = "User should not be empty")
    private UserDto userDto;
    @NotNull(message = "Tour should not be empty")
    private TourDto tourDto;

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Boolean getIsConfirmed() {
        return isConfirmed;
    }

    public void setIsConfirmed(Boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public TourDto getTourDto() {
        return tourDto;
    }

    public void setTourDto(TourDto tourDto) {
        this.tourDto = tourDto;
    }
}
