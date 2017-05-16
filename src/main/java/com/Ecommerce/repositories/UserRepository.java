package com.Ecommerce.repositories;

import com.Ecommerce.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Turian Ovidiu.
 * This is the interface for User Repository.
 */
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);
}
