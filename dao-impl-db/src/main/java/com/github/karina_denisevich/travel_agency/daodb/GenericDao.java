package com.github.karina_denisevich.travel_agency.daodb;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, PK extends Serializable> {

    T get(PK id);

    PK insert(T entity);

    void update(T entity);

    void delete(PK id);

    List<T> getAll();
}
