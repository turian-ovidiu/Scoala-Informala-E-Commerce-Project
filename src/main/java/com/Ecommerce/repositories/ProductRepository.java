package com.Ecommerce.repositories;

import com.Ecommerce.domain.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Ovi on 4/11/2017.
 */
public interface ProductRepository extends CrudRepository<Product,Integer> {

    List<Product> findAllByDescriptionContaining(String description);
    List<Product> findAllByNameContaining(String name);

    @Override
    long count();
}
