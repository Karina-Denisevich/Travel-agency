package com.github.karina_denisevich.travel_agency.datamodel;

import java.io.Serializable;

public abstract class AbstractModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AbstractModel{" +
                "id=" + id +
                '}';
    }
}
