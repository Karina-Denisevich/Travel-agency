package com.github.karina_denisevich.travel_agency.daoxml.impl;

import com.github.karina_denisevich.travel_agency.annotation.DbTableAnalyzer;
import com.github.karina_denisevich.travel_agency.daoapi.GenericDao;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Repository
public abstract class GenericDaoXmlImpl<T, PK extends Serializable> implements GenericDao<T, PK> {

    private final Class<T> genericType;

    private final String tableName;

    @SuppressWarnings("unchecked")
    public GenericDaoXmlImpl() {

        this.genericType = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];

        this.tableName = new DbTableAnalyzer().getDbTableName(genericType);
    }

    @Override
    public T get(PK id) {
        return null;
    }

    @Override
    public PK insert(T entity) {
        return null;
    }

    @Override
    public void update(T entity) {

    }

    @Override
    public void delete(PK id) {

    }

    @Override
    public List<T> getAll() {
        return null;
    }
}
