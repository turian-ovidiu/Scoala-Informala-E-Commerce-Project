package com.Ecommerce.services;


import com.Ecommerce.domain.Cart;
import com.Ecommerce.repositories.CartRepository;
import com.Ecommerce.services.reposervices.CartServiceImpRepo;
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
 * This class represent the Cart service unit tests.
 */
public class CartServiceTest {


    private CartServiceImpRepo cartServiceImpRepo;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private Cart cart;

    @Mock
    private List<Cart> carts;

    @Before
    public void setupMock(){
        MockitoAnnotations.initMocks(this);
        cartServiceImpRepo = new CartServiceImpRepo();
        cartServiceImpRepo.setCartRepository(cartRepository);
        carts = new ArrayList<>();
    }


    @Test
    public void shouldReturnCart_whenGetByIdIsCalled() throws Exception{
        when(cartRepository.findOne(2)).thenReturn(cart);
        Cart retrievedCart = cartServiceImpRepo.getById(2);
        assertThat(retrievedCart,is(equalTo(cart)));
    }

    @Test
    public void shouldReturnCart_whenSaveOrUpdateIsCalled() throws Exception{
        when(cartRepository.save(cart)).thenReturn(cart);
        Cart savedCart = cartServiceImpRepo.saveOrUpdate(cart);
        assertThat(savedCart,is(equalTo(cart)));
    }

    @Test
    public void shouldCallDeleteMethodOfCartRepository_whenDeleteIsCalled() throws Exception{
        doNothing().when(cartRepository).delete(2);
        CartRepository crepo = Mockito.mock(CartRepository.class);
        cartServiceImpRepo.delete(2);
        verify(cartRepository,times(1)).delete(2);
    }


    @Test
    public void shouldReturnListCart_whenListAllIsCalled() throws Exception{
        when(cartRepository.findAll()).thenReturn(carts);
        List<Cart> cartList = (List<Cart>) cartServiceImpRepo.listAll();
        assertThat(cartList,is(equalTo(carts)));

    }
}
