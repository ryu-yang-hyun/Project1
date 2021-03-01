package com.example.springboot.service.generic;
import com.example.springboot.exception.ServiceException;

import java.io.Serializable;
import java.util.List;

public interface BaseService<K extends Serializable & Comparable<K>, E extends BaseDomain<K, ?>> {
    void save(E entity) throws ServiceException;
    void update(E entity) throws ServiceException;
    void create(E entity) throws ServiceException;
    void delete(E entity) throws ServiceException;

    void delete(Iterable<E> entities) throws ServiceException;

    E getById(K id);
    List<E> list();
    Long count();
    void flush();
}
