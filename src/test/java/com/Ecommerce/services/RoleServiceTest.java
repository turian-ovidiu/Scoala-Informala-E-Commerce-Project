package com.Ecommerce.services;


import com.Ecommerce.domain.security.Role;
import com.Ecommerce.repositories.RoleRepository;
import com.Ecommerce.services.reposervices.RoleServiceImpRepo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Turian Ovidiu.
 * This class represent the Role service unit tests.
 */
public class RoleServiceTest {

    private RoleServiceImpRepo roleServiceImpRepo;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private Role role;

    @Mock
    List<Role> roles;


    @Before
    public void setupMock(){
        MockitoAnnotations.initMocks(this);
        roleServiceImpRepo = new RoleServiceImpRepo();
        roleServiceImpRepo.setRoleRepsoitory(roleRepository);
        roles = new ArrayList<>();
    }

    @Test
    public void shouldReturnRole_whenGetByIdIsCalled() throws Exception{
        when(roleRepository.findOne(5)).thenReturn(role);
        Role retievedRole = roleServiceImpRepo.getById(5);
        assertThat(retievedRole,is(equalTo(role)));
    }

    @Test
    public void shouldReturnRole_whenSaveOrUpdateIsCalled() throws Exception{
        when(roleRepository.save(role)).thenReturn(role);
        Role savedRole = roleServiceImpRepo.saveOrUpdate(role);
        assertThat(savedRole,is(equalTo(role)));
    }

    @Test
    public void shouldCallDeleteMethodOfRoleRepository_whenDeleteIsCalled() throws Exception{
        doNothing().when(roleRepository).delete(5);
        RoleRepository rrepo = Mockito.mock(RoleRepository.class);
        roleServiceImpRepo.delete(5);
        verify(roleRepository,times(1)).delete(5);
    }

    @Test
    public void shouldReturnListRole_whenListAllIsCalled() throws Exception{
        when(roleRepository.findAll()).thenReturn(roles);
        List<Role> roleList = (List<Role>) roleServiceImpRepo.listAll();
        assertThat(roleList,is(equalTo(roles)));
    }
}
