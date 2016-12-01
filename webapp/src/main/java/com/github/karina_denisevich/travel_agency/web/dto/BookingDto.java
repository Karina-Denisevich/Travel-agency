package com.github.karina_denisevich.travel_agency.web.dto;

public class BookingDto extends AbstractDto {

    private String orderDate;
    private Boolean isConfirmed;
    private UserDto userDto;
    private TourDto tourDto;

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String  orderDate) {
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
