package com.github.karina_denisevich.travel_agency.daodb;

import com.github.karina_denisevich.travel_agency.datamodel.AbstractModel;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, PK extends Serializable> {

    T get(PK id);

    PK insert(T entity);

    void update(T entity);

    void delete(PK id);

    List<T> getAll();

    void insertBatch(List<T> tList);
}
