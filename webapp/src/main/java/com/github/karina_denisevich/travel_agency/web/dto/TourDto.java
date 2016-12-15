package com.github.karina_denisevich.travel_agency.web.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

public class TourDto extends AbstractDto{

    @NotEmpty(message = "Title should not be empty")
    private String title;
    private String photoLink;
    private Boolean isHot;
    @Min(value = 0, message = "Price should be grater than 0")
    @NotNull(message = "Price should not be null")
    private Double price;
    private String description;
    private List<CategoryDto> categoryDtoList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public Boolean getIsHot() {
        return isHot;
    }

    public void setIsHot(Boolean hot) {
        isHot = hot;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CategoryDto> getCategoryList() {
        return categoryDtoList;
    }

    public void setCategoryList(List<CategoryDto> categoryDtoList) {
        this.categoryDtoList = categoryDtoList;
    }

    @Override
    public String toString() {
        return "Tour{" +
                "title='" + title + '\'' +
                ", photoLink='" + photoLink + '\'' +
                ", isHot=" + isHot +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
