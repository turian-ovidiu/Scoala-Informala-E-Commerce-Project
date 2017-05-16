package com.Ecommerce.services;

import com.Ecommerce.domain.User;

/**
 * Created by Turian Ovidiu.
 * This is the interface for UserService implementation.
 */
public interface UserService extends CRUDService<User>{

    User findByUsername(String username);
    Integer countCartProducts(User user);
}
