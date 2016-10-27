package com.github.karina_denisevich.travel_agency.datamodel;

import com.github.karina_denisevich.travel_agency.annotation.DbTable;

import java.util.List;

@DbTable(name = "category")
public class Category extends AbstractModel {

    private enum CategoryEnum {
        ESCOURTED_TOUR,
        RAIL_TOUR,
        BUS_TOUR,
        SHOP_TOUR,
        SAFARI_TOUR,
        BEACH_TOUR,
        OTHER_TOUR
    }

    private Category type;
    private List<Tour> tourList;

    public Category getCategory() {
        return type;
    }

    public void setCategory(Category type) {
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
