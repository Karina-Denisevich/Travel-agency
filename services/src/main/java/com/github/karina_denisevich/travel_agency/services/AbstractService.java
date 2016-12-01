package com.github.karina_denisevich.travel_agency.services;

import java.util.List;

public interface AbstractService<T> {

    Long save(T entity);

    void saveAll(List<T> entities);

    T get(Long id);

    List<T> getAll();

    void delete(Long id);
}
