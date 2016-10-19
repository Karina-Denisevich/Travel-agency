package com.github.karina_denisevich.travel_agency.datamodel;

public class TourDescription extends AbstractModel {

    private String fullDescription;
    private String shortDescription;

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
}
