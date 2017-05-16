package com.Ecommerce.services.reposervices;

import com.Ecommerce.domain.security.Role;
import com.Ecommerce.repositories.RoleRepository;
import com.Ecommerce.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Turian Ovidiu.
 * This class represent the implementation of RoleService interface.
 */
@Service
@Transactional(readOnly = true)
public class RoleServiceImpRepo implements RoleService {

    private RoleRepository roleRepsoitory;

    @Autowired
    public void setRoleRepsoitory(RoleRepository roleRepsoitory) {
        this.roleRepsoitory = roleRepsoitory;
    }


    @Override
    public List<?> listAll() {
        List<Role> roles = new ArrayList<>();
        roleRepsoitory.findAll().forEach(roles::add);
        return roles;
    }

    @Override
    public Role getById(Integer id) {
        return roleRepsoitory.findOne(id);
    }

    @Override
    @Transactional(readOnly = false)
    public Role saveOrUpdate(Role domainObject) {
        return roleRepsoitory.save(domainObject);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Integer id) {
        roleRepsoitory.delete(id);
    }
}
