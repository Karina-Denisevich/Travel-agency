package com.github.karina_denisevich.travel_agency.services.impl;

import com.github.karina_denisevich.travel_agency.daoapi.GenericDao;
import com.github.karina_denisevich.travel_agency.datamodel.AbstractModel;
import com.github.karina_denisevich.travel_agency.services.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Service
@SuppressWarnings("unchecked")
public abstract class AbstractServiceImpl<T extends AbstractModel, PK extends Serializable>
        implements AbstractService<T, PK> {

    @Inject
    private GenericDao<T, PK> genericDao;

    @Transactional
    @Override
    public PK save(T entity) {
        if (entity.getId() == null) {
            return genericDao.insert(entity);
        } else {
            if (genericDao.update(entity) == 0) {
                return null;
            }
            return (PK) entity.getId();
        }
    }

    @Transactional
    @Override
  //  @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void saveAll(List<T> entities) {
        entities.forEach(this::save);
    }

    @Override
    public T get(PK id) {
        return genericDao.get(id);
    }

    @Override
    public List<T> getAll() {
        return genericDao.getAll();
    }

    @Transactional
    @Override
    public int delete(PK id) {
        return genericDao.delete(id);
    }
}
