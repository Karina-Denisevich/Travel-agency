package com.github.karina_denisevich.travel_agency.web.dto;

import com.github.karina_denisevich.travel_agency.datamodel.Category;

import java.util.List;

public class TourDto extends AbstractDto{

    private String title;
    private String photoLink;
    private Boolean isHot;
    private Double price;
    private String description;
    private List<Category> categoryList; //TODO: change to Dto

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

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
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
