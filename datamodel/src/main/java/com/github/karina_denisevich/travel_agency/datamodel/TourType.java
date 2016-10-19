package com.github.karina_denisevich.travel_agency.datamodel;

public class TourType extends AbstractModel {

    private enum TypeEnum{
        ESCOURTED_TOUR,
        RAIL_TOUR,
        BUS_TOUR,
        SHOP_TOUR,
        SAFARI_TOUR,
        BEACH_TOUR,
        OTHER_TOUR;
    }

    private TypeEnum type;

    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }
}
