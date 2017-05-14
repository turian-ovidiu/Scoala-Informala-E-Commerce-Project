package com.Ecommerce.services;

import com.Ecommerce.domain.User;

/**
 * Created by Ovi on 5/14/2017.
 */
public interface UserService extends CRUDService<User>{

    User findByUsername(String username);
    Integer countCartProducts(User user);
}
