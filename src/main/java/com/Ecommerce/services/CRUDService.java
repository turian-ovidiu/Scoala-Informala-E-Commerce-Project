package com.Ecommerce.services;

import java.util.List;

/**
 * Created by Turian Ovidiu.
 * This is the interface for all Service interfaces.
 */
public interface CRUDService<T> {

    List<?> listAll();

    T getById(Integer id);

    T saveOrUpdate(T domainObject);

    void delete(Integer id);
}
