package com.inn.warehouses.utils;

import java.util.List;

public interface GenericService<T, E extends T, ID> {

    void failIfExistsById(ID id);

    void create(E entity);

    void failIfNotExistsById(ID id);

    void update(ID id, T entity);

    List<E> findAll();
    
    E findById(ID id);

    void delete(ID id);
}