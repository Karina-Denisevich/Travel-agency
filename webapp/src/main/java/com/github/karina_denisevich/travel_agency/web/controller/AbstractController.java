package com.github.karina_denisevich.travel_agency.web.controller;

import com.github.karina_denisevich.travel_agency.datamodel.AbstractModel;
import com.github.karina_denisevich.travel_agency.services.AbstractService;
import com.github.karina_denisevich.travel_agency.web.dto.AbstractDto;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("unchecked")
public abstract class AbstractController<T extends AbstractModel,
        D extends AbstractDto, PK extends Serializable> {

    @Inject
    private AbstractService<T, PK> abstractService;

    @Inject
    private ConversionServiceFactoryBean conversionService;

    private final Class<T> genericType;
    private final Class<D> genericDtoType;

    protected AbstractController() {
        this.genericType = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
        this.genericDtoType = (Class<D>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[1];
    }

    @RequestMapping(value = "/{entityId}", method = RequestMethod.GET)
    public ResponseEntity<D> getById(@PathVariable PK entityId) {
        return new ResponseEntity<>(conversionService.getObject()
                .convert(abstractService.get(entityId), genericDtoType), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<D>> getAll() {
        List<T> entities = abstractService.getAll();

        if (CollectionUtils.isEmpty(entities)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<D> convertedList = (List<D>) conversionService.getObject().convert(entities,
                TypeDescriptor.valueOf(List.class),
                TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(genericDtoType)));

        return new ResponseEntity<>(convertedList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> create(@RequestBody D entityDto) {
        T entity = (conversionService.getObject().convert(entityDto, genericType));
        entity.setId(null);
        abstractService.save(entity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/saveAll", method = RequestMethod.POST)
    public ResponseEntity<Object> createBatch(@RequestBody List<D> entityDtoList) {
        List<T> convertedList = (List<T>) conversionService.getObject().convert(entityDtoList,
                TypeDescriptor.valueOf(List.class),
                TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(genericType)));
        abstractService.saveAll(convertedList);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{entityId}", method = RequestMethod.PUT)
    public ResponseEntity<Object> update(@RequestBody D entityDto,
                                         @PathVariable Long entityId) {
        T entity = (conversionService.getObject().convert(entityDto, genericType));
        entity.setId(entityId);
        if (!Objects.equals(abstractService.save(entity), entityId)) {
            return new ResponseEntity<>("There is no entity with id = " + entityId,
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{entityId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable PK entityId) {
        if (abstractService.delete(entityId) == 0) {
            return new ResponseEntity<>("There is no entity with id = " + entityId,
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
