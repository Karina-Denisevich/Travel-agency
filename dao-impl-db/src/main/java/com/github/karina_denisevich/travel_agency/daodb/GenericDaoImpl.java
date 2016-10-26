package com.github.karina_denisevich.travel_agency.daodb;

import com.github.karina_denisevich.travel_agency.annotation.DbTable;
import com.google.common.base.CaseFormat;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Repository
public abstract class GenericDaoImpl<T, PK extends Serializable> implements GenericDao<T, PK> {

    @Inject
    private JdbcTemplate jdbcTemplate;

    private final Class<T> genericType;

    private final String tableName;

    @SuppressWarnings("unchecked")
    public GenericDaoImpl() {

        this.genericType = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];

        if (genericType.isAnnotationPresent(DbTable.class)) {
            Annotation annotation = genericType.getAnnotation(DbTable.class);
            DbTable dbTable = (DbTable) annotation;
            if (!dbTable.name().isEmpty()) {
                tableName = dbTable.name();
            } else {
                tableName = getTableName(genericType.getSimpleName());
            }
        } else {
            tableName = getTableName(genericType.getSimpleName());
        }
    }

    private String getTableName(String className){
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, className);
    }

    @Override
    public T get(PK id) {
        String sql = "SELECT * FROM " + tableName + " WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{id},
                new BeanPropertyRowMapper<>(genericType));
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

    @Override
    public void insertBatch(List<T> tList) {

    }
}