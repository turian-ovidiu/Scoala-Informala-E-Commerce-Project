package com.Ecommerce.services;

import java.util.List;

/**
 * Created by Ovi on 5/14/2017.
 */
public interface CRUDService<T> {

    List<?> listAll();

    T getById(Integer id);

    T saveOrUpdate(T domainObject);

    void delete(Integer id);
}
