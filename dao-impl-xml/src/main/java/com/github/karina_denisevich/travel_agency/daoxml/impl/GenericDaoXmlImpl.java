package com.github.karina_denisevich.travel_agency.daoxml.impl;

import com.github.karina_denisevich.travel_agency.annotation.DbTableAnalyzer;
import com.github.karina_denisevich.travel_agency.daoapi.GenericDao;
import com.github.karina_denisevich.travel_agency.daoapi.exception.DuplicateEntityException;
import com.github.karina_denisevich.travel_agency.daoapi.exception.EmptyResultException;
import com.github.karina_denisevich.travel_agency.daoxml.util.XmlFileIOUtils;
import com.github.karina_denisevich.travel_agency.datamodel.AbstractModel;
import com.github.karina_denisevich.travel_agency.datamodel.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Repository
public abstract class GenericDaoXmlImpl<T extends AbstractModel, PK extends Serializable>
        implements GenericDao<T, PK> {

    private final Class<T> genericType;

    protected XmlFileIOUtils<T> xmlFileIOUtils;

    @Value("${basePath}")
    private String basePath;

    @PostConstruct
    private void initialize() throws IOException {

        String fileName = basePath.concat("\\")
                .concat(new DbTableAnalyzer().getDbTableName(genericType)).concat(".xml");
        xmlFileIOUtils = new XmlFileIOUtils<>(fileName, genericType);
    }

    @SuppressWarnings("unchecked")
    public GenericDaoXmlImpl() {
        this.genericType = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    @Override
    public T get(PK id) {
        List<T> entityList = xmlFileIOUtils.readCollection();

        for (T entity : entityList) {
            if (entity.getId().equals(id)) {
                return entity;
            }
        }
        throw new EmptyResultException("There is no entity with id = " + id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public PK insert(T entity) {
        List<T> entityList = xmlFileIOUtils.readCollection();
        checkDuplicateEmail(entity, entityList);
        Long id;
        if (entity.getId() == null) {
            id = getNextId(entityList);
            entity.setId(id);
        } else {
            id = entity.getId();
        }

        entityList.add(entity);
        xmlFileIOUtils.writeCollection(entityList);

        return (PK) id;
    }

    @Override
    public void update(T entity) {
        List<T> entityList = xmlFileIOUtils.readCollection();

        for (T t : entityList) {
            if (t.getId().equals(entity.getId())) {
                entityList.set(entityList.indexOf(t), entity);
                break;
            }
        }
        xmlFileIOUtils.writeCollection(entityList);
    }

    @Override
    public void delete(PK id) {
        List<T> entityList = xmlFileIOUtils.readCollection();

        for (T entity : entityList) {
            if (entity.getId().equals(id)) {
                entityList.remove(entity);
                break;
            }
        }
        xmlFileIOUtils.writeCollection(entityList);
    }

    @Override
    public List<T> getAll() {
        return xmlFileIOUtils.readCollection();
    }

    @SuppressWarnings("unchecked")
    protected void checkDuplicateEmail(T entity, List<T> entitiesFromXml) {
        if (genericType.getSimpleName().equals("User")) {
            User user = (User) entity;
            for (User type : (List<User>) entitiesFromXml) {
                if (type.getEmail().equals(user.getEmail())) {
                    throw new DuplicateEntityException("There is already user with email = " +
                            user.getEmail());
                }
            }
        }
    }

    protected Long getNextId(List<T> entityList) {
        return entityList.isEmpty() ? 1L : entityList.get(
                entityList.size() - 1).getId() + 1;
    }
}
