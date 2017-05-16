package com.Ecommerce.services;

import com.Ecommerce.domain.CartDetail;
import com.Ecommerce.repositories.CartDetailRepository;
import com.Ecommerce.services.reposervices.CartDetailServiceImpRepo;
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
 * This class represent the Cart Detail service unit tests.
 */
public class CartDetailServiceTest {


    private CartDetailServiceImpRepo cartDetailServiceImpRepo;

    @Mock
    private CartDetailRepository cartDetailRepository;

    @Mock
    private CartDetail cartDetail;

    @Mock
    private List<CartDetail> cartDetails;


    @Before
    public void setupMock(){
        MockitoAnnotations.initMocks(this);
        cartDetailServiceImpRepo = new CartDetailServiceImpRepo();
        cartDetailServiceImpRepo.setCartDetailRepository(cartDetailRepository);
        cartDetails = new ArrayList<>();
    }

    @Test
    public void shouldReturnCartDetail_whenGetByIdIsCalled() throws Exception{
        when(cartDetailRepository.findOne(1)).thenReturn(cartDetail);
        CartDetail retrievedCartDetail = cartDetailServiceImpRepo.getById(1);
        assertThat(retrievedCartDetail,is(equalTo(cartDetail)));
    }

    @Test
    public void shouldReturnCartDetail_whenSaveOrUpdateIsCalled() throws Exception{
        when(cartDetailRepository.save(cartDetail)).thenReturn(cartDetail);
        CartDetail savedCartDetail = cartDetailServiceImpRepo.saveOrUpdate(cartDetail);
        assertThat(savedCartDetail,is(equalTo(cartDetail)));
    }

    @Test
    public void shouldCallDeleteMethodOfCartDetailRepository_whenDeleteIsCalled() throws Exception{
        doNothing().when(cartDetailRepository).delete(1);
        CartDetailRepository cdrepo = Mockito.mock(CartDetailRepository.class);
        cartDetailServiceImpRepo.delete(1);
        verify(cartDetailRepository,times(1)).delete(1);
    }

    @Test
    public void shouldReturnListCartDetail_whenListAllIsCalled() throws Exception{
        when(cartDetailRepository.findAll()).thenReturn(cartDetails);
        List<CartDetail> cartDetailList = (List<CartDetail>) cartDetailServiceImpRepo.listAll();
        assertThat(cartDetailList,is(equalTo(cartDetails)));
    }
}
