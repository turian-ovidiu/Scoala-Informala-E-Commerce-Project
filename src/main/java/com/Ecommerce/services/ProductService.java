package com.Ecommerce.services;

import com.Ecommerce.domain.Product;
import com.Ecommerce.domain.User;

import java.util.List;

/**
 * Created by Ovi on 5/14/2017.
 */
public interface ProductService extends CRUDService<Product>{

    List<Product> findByDescription(String description);
    List<Product> findByName(String name);
    Long countAll();

    void addProduct(User user, Product product);
}
