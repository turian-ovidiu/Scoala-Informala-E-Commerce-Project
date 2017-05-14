package com.Ecommerce.repositories;

import com.Ecommerce.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Ovi on 4/11/2017.
 */
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);
}
