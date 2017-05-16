package com.Ecommerce.repositories;

import com.Ecommerce.domain.security.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Turian Ovidiu.
 * This is the interface for Role Repository.
 */
public interface RoleRepository extends CrudRepository<Role,Integer> {
}
