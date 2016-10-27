package com.github.karina_denisevich.travel_agency.datamodel;

import com.github.karina_denisevich.travel_agency.annotation.DbTable;

import java.util.List;

@DbTable(name = "category")
public class Category extends AbstractModel {

    public enum CategoryEnum {
        ESCOURTED_TOUR,
        RAIL_TOUR,
        BUS_TOUR,
        SHOP_TOUR,
        SAFARI_TOUR,
        BEACH_TOUR,
        OTHER_TOUR
    }

    private CategoryEnum type;
    private List<Tour> tourList;

    public CategoryEnum getType() {
        return type;
    }

    public void setType(CategoryEnum type) {
        this.type = type;
    }

    public List<Tour> getTourList() {
        return tourList;
    }

    public void setTourList(List<Tour> tourList) {
        this.tourList = tourList;
    }

    @Override
    public String toString() {
        return "Category{" +
                "type=" + type +
                '}';
    }
}
