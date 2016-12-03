package com.github.karina_denisevich.travel_agency.services.impl;

import com.github.karina_denisevich.travel_agency.daoapi.GenericDao;
import com.github.karina_denisevich.travel_agency.services.AbstractService;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class AbstractServiceImpl<T, PK extends Serializable>
        implements AbstractService<T, PK> {

    private final Class<T> genericType;
    private GenericDao<T, PK> genericDao;

    @SuppressWarnings("unchecked")
    protected  AbstractServiceImpl(){
        this.genericType = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    @Override
    public PK save(T entity) {
        return null;
    }

    @Override
    public void saveAll(List<T> entities) {

    }

    @Override
    public T get(PK id) {
        return genericDao.get(id);
    }

    @Override
    public List<T> getAll() {
        return genericDao.getAll();
    }

    @Override
    public int delete(PK id) {
        return 0;
    }
}
