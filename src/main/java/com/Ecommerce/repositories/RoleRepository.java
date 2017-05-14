package com.Ecommerce.repositories;

import com.Ecommerce.domain.security.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Ovi on 4/11/2017.
 */
public interface RoleRepository extends CrudRepository<Role,Integer> {
}
