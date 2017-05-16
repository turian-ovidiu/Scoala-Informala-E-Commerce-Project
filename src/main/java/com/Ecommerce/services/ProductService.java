package com.Ecommerce.services;

import com.Ecommerce.domain.Product;
import com.Ecommerce.domain.User;

import java.util.List;

/**
 * Created by Turian Ovidiu.
 * This is the interface for ProductService implementation.
 */
public interface ProductService extends CRUDService<Product>{

    List<Product> findByDescription(String description);
    List<Product> findByName(String name);
    Long countAll();

    void addProduct(User user, Product product);
}
