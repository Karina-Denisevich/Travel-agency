package com.github.karina_denisevich.travel_agency.daodb.impl;

import com.github.karina_denisevich.travel_agency.annotation.DbTableAnalyzer;
import com.github.karina_denisevich.travel_agency.daoapi.GenericDao;
import com.github.karina_denisevich.travel_agency.daoapi.exception.DuplicateEntityException;
import com.github.karina_denisevich.travel_agency.daoapi.exception.EmptyResultException;
import com.github.karina_denisevich.travel_agency.daodb.unmapper.RowUnmapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public abstract class GenericDaoDbImpl<T, PK extends Serializable> implements GenericDao<T, PK> {

    @Inject
    private JdbcTemplate jdbcTemplate;

    private final Class<T> genericType;

    private final RowUnmapper<T> rowUnmapper;

    protected final String tableName;

    @SuppressWarnings("unchecked")
    protected GenericDaoDbImpl(RowUnmapper<T> rowUnmapper) {
        this.rowUnmapper = rowUnmapper;
        this.genericType = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
        this.tableName = new DbTableAnalyzer().getDbTableName(genericType);
    }

    @Override
    public T get(PK id) {
        String sql = "SELECT * FROM " + tableName + " WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id},
                    new BeanPropertyRowMapper<>(genericType));
        } catch (EmptyResultDataAccessException ex) {
            throw new EmptyResultException("There is no entity with id = " + id);
        }
    }

    /**
     * Methods work both for auto-generated keys, and for not.
     *
     * @param entity
     * @return primary key for table
     */
    @Override
    @SuppressWarnings("unchecked")
    public PK insert(T entity) {
        Map map = rowUnmapper.mapColumns(entity);
        SimpleJdbcInsert insertEntity;

        try {
            if (map.containsKey("id")) {
                insertEntity = new SimpleJdbcInsert(jdbcTemplate)
                        .withTableName(tableName);
                insertEntity.execute(map);
                return (PK) map.get("id");
            } else {
                insertEntity = new SimpleJdbcInsert(jdbcTemplate)
                        .withTableName(tableName)
                        .usingGeneratedKeyColumns("id");
                return (PK) insertEntity.executeAndReturnKey(map);
            }
        } catch (DuplicateKeyException ex) {
            throw new DuplicateEntityException(ex.getCause().getMessage());
        }
    }

    @Override
    public int update(T entity) {
        Map<String, Object> map = rowUnmapper.mapColumns(entity);
        try {
            return jdbcTemplate.update(generateUpdateSql(map), getUpdateParameters(map));
        } catch (DuplicateKeyException ex) {
            throw new DuplicateEntityException(ex.getCause().getMessage());
        }
    }

    @Override
    public int delete(PK id) {
        String sql = "DELETE FROM " + tableName + " WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public List<T> getAll() {
        String sql = "SELECT * FROM " + tableName;
        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(genericType));
    }

    private String generateUpdateSql(Map<String, Object> map) {
        StringBuilder sql = new StringBuilder("UPDATE " + tableName + " SET ");

        map.entrySet().stream().filter(entry -> !Objects.equals(entry.getKey(), "id"))
                .forEach(entry -> sql.append(entry.getKey()).append(" = ?, "));
        sql.deleteCharAt(sql.lastIndexOf(","));
        sql.append("WHERE id = ?");

        return sql.toString();
    }

    private Object[] getUpdateParameters(Map<String, Object> map) {
        Object[] valueArr = new Object[map.size()];
        int index = 0;
        for (Object o : map.values()) {
            valueArr[index] = o;
            index++;
        }
        return valueArr;
    }
}
