package com.github.karina_denisevich.travel_agency.web.dto;

public abstract class AbstractDto {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AbstractDto{" +
                "id=" + id +
                '}';
    }
}
