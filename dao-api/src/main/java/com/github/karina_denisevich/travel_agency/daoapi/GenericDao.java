package com.github.karina_denisevich.travel_agency.daoapi;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, PK extends Serializable> {

    T get(PK id);

    PK insert(T entity);

    int update(T entity);

    int delete(PK id);

    List<T> getAll();
}
