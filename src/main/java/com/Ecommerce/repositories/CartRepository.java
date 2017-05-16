package com.Ecommerce.repositories;

import com.Ecommerce.domain.Cart;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Turian Ovidiu.
 * This is the interface for Cart Repository.
 */
public interface CartRepository extends CrudRepository<Cart,Integer> {
}
