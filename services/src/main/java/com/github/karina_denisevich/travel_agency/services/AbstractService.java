package com.github.karina_denisevich.travel_agency.services;

import java.io.Serializable;
import java.util.List;

public interface AbstractService<T, PK extends Serializable> {

    PK save(T entity);

    void saveAll(List<T> entities);

    T get(PK id);

    List<T> getAll();

    int delete(PK id);
}
