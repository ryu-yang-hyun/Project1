package com.example.springboot.service.generic;

import com.example.springboot.exception.ServiceException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by alex on 2017. 7. 31..
 */
public class BaseServiceImpl<K extends Serializable & Comparable<K>, E extends BaseDomain<K, ?>> implements BaseService<K, E> {

    private Class<E> objectClass;
    private JpaRepository<E, K> repository;

    public BaseServiceImpl(JpaRepository<E, K> repository) {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.objectClass = (Class<E>) genericSuperclass.getActualTypeArguments()[1];
        this.repository = repository;
    }

    protected final Class<E> getObjectClass() {
        return objectClass;
    }

    @Override
    public void save(E entity) throws ServiceException {
        repository.saveAndFlush(entity);
    }

    @Override
    public void update(E entity) throws ServiceException {
        save(entity);
    }

    @Override
    public void create(E entity) throws ServiceException {
        save(entity);
    }

    @Override
    public void delete(E entity) throws ServiceException {
        repository.delete(entity);
    }


    @Override
    public void delete(Iterable<E> entities) throws ServiceException {
        repository.deleteInBatch(entities);
    }

    @Override
    public E getById(K id) {
        return null;
    }


    @Override
    public List<E> list() {
        return repository.findAll();
    }

    @Override
    public Long count() {
        return repository.count();
    }

    @Override
    public void flush() {
        repository.flush();
    }
}
