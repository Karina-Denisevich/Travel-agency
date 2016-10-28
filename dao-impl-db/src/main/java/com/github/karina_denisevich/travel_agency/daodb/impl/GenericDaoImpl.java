package com.github.karina_denisevich.travel_agency.daodb.impl;

import com.github.karina_denisevich.travel_agency.annotation.DbTable;
import com.github.karina_denisevich.travel_agency.daodb.GenericDao;
import com.github.karina_denisevich.travel_agency.daodb.unmapper.RowUnmapper;
import com.google.common.base.CaseFormat;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

@Repository
public abstract class GenericDaoImpl<T, PK extends Serializable> implements GenericDao<T, PK> {

    @Inject
    private JdbcTemplate jdbcTemplate;

    private final Class<T> genericType;

    private final RowUnmapper<T> rowUnmapper;

    private final String tableName;

    @SuppressWarnings("unchecked")
    public GenericDaoImpl(RowUnmapper<T> rowUnmapper) {

        this.rowUnmapper = rowUnmapper;

        this.genericType = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];

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

    private String getTableName(String className) {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, className);
    }

    @Override
    public T get(PK id) {
        String sql = "SELECT * FROM " + tableName + " WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{id},
                new BeanPropertyRowMapper<>(genericType));
    }

    @Override
    @SuppressWarnings("unchecked")
    public PK insert(T entity) {
        Map map = rowUnmapper.mapColumns(entity);
        SimpleJdbcInsert insertEntity;

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
    }

    @Override
    public void update(T entity) {
        StringBuilder sql = new StringBuilder("UPDATE " + tableName + " SET ");

        Map<String, Object> map = rowUnmapper.mapColumns(entity);
        Object[] valueArr = new Object[map.size()];

        int index = 0;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (index < map.size() - 1) {
                sql.append(entry.getKey().concat(" = ?"));
                if (index < map.size() - 2) {
                    sql.append(",");
                }
                sql.append(" ");
            }
            valueArr[index] = entry.getValue();
            index++;
        }
        sql.append("WHERE id = ?");

        jdbcTemplate.update(sql.toString(), valueArr);
    }

    @Override
    public void delete(PK id) {
        final String sql = "DELETE FROM " + tableName + " WHERE id = ?";

        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<T> getAll() {

        final String sql = "SELECT * FROM " + tableName;

        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(genericType));
    }

    @Override
    @Transactional
    public void insertBatch(List<T> tList) {

    }
}
