package com.github.karina_denisevich.travel_agency.datamodel;

public class Tour extends AbstractModel {

    private String title;
    private String photoLink;
    private boolean isHot;


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

    public boolean isHot() {
        return isHot;
    }

    public void setHot(boolean hot) {
        isHot = hot;
    }
}
