package com.Ecommerce.services;


import com.Ecommerce.domain.User;
import com.Ecommerce.repositories.UserRepository;
import com.Ecommerce.services.reposervices.UserServiceImpRepo;
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
 * This class represent the User service unit tests.
 */

public class UserServiceTest {

    private UserServiceImpRepo userServiceImpRepo;

    @Mock
    private UserRepository userRepository;

    @Mock
    private User user;

    @Mock
    private List<User> users;


    @Before
    public void setupMock(){
        MockitoAnnotations.initMocks(this);
        userServiceImpRepo = new UserServiceImpRepo();
        userServiceImpRepo.setUserRepository(userRepository);
        users = new ArrayList<>();

    }


    @Test
    public void shouldReturnUser_whenGetByIdIsCalled(){
        when(userRepository.findOne(3)).thenReturn(user);
        User retrievedUser = userServiceImpRepo.getById(3);
        assertThat(retrievedUser,is(equalTo(user)));
    }

    @Test
    public void shouldReturnUser_whenSaveOrUpdateIsCalled() throws Exception{
        when(userRepository.save(user)).thenReturn(user);
        User savedUser = userServiceImpRepo.saveOrUpdate(user);
        assertThat(savedUser,is(equalTo(user)));
    }

    @Test
    public void shouldCallDeleteMethodOfUserRepository_whenDeleteIsCalled() throws  Exception{
        doNothing().when(userRepository).delete(3);
        UserRepository urepo = Mockito.mock(UserRepository.class);
        userServiceImpRepo.delete(3);
        verify(userRepository,times(1)).delete(3);
    }

    @Test
    public void shouldReturnUser_whenFindByNameIsCalled(){
        when(userRepository.findByUsername("Jack")).thenReturn(user);
        User savedUser = userServiceImpRepo.findByUsername("Jack");
        assertThat(savedUser,is(equalTo(user)));
    }

    @Test
    public void shouldReturnListUsers_whenListAllIsCalled(){
        when(userRepository.findAll()).thenReturn(users);
        List<User> userList = (List<User>) userServiceImpRepo.listAll();
        assertThat(userList,is(equalTo(users)));

    }

}
