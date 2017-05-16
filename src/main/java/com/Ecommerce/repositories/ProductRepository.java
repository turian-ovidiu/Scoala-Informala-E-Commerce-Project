package com.Ecommerce.repositories;

import com.Ecommerce.domain.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Turian Ovidiu.
 * This is the interface for Product Repository.
 */
public interface ProductRepository extends CrudRepository<Product,Integer> {

    List<Product> findAllByDescriptionContaining(String description);
    List<Product> findAllByNameContaining(String name);

    @Override
    long count();
}
