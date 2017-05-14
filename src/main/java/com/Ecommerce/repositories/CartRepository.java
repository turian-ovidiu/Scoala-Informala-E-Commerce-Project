package com.Ecommerce.repositories;

import com.Ecommerce.domain.Cart;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Ovi on 5/14/2017.
 */
public interface CartRepository extends CrudRepository<Cart,Integer> {
}
