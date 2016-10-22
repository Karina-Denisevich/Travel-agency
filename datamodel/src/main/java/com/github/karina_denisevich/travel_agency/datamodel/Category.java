package com.github.karina_denisevich.travel_agency.datamodel;

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

    public Category getCategory() {
        return type;
    }

    public void setCategory(Category type) {
        this.type = type;
    }
}
