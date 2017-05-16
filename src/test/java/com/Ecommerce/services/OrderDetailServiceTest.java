package com.Ecommerce.services;

import com.Ecommerce.domain.OrderDetail;
import com.Ecommerce.repositories.OrderDetailRepository;
import com.Ecommerce.services.reposervices.OrderDetailServiceImpRepo;
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
 * This class represent the Order Detail service unit tests.
 */
public class OrderDetailServiceTest {


    private OrderDetailServiceImpRepo orderDetailServiceImpRepo;

    @Mock
    private OrderDetailRepository orderDetailRepository;

    @Mock
    private OrderDetail orderDetail;

    @Mock
    private List<OrderDetail> orderDetails;

    @Before
    public void setupMock(){
        MockitoAnnotations.initMocks(this);
        orderDetailServiceImpRepo = new OrderDetailServiceImpRepo();
        orderDetailServiceImpRepo.setOrderDetailRepository(orderDetailRepository);
        orderDetails = new ArrayList<>();
    }

    @Test
    public void shouldReturnOrderDetail_whenGetByIdIsCalled() throws Exception{
        when(orderDetailRepository.findOne(3)).thenReturn(orderDetail);
        OrderDetail retrievedOrderDetail = orderDetailServiceImpRepo.getById(3);
        assertThat(retrievedOrderDetail,is(equalTo(orderDetail)));
    }

    @Test
    public void shouldReturnOrderDetail_whenSaveOrUpdateIsCalled() throws Exception{
        when(orderDetailRepository.save(orderDetail)).thenReturn(orderDetail);
        OrderDetail savedOrderDetail = orderDetailServiceImpRepo.saveOrUpdate(orderDetail);
        assertThat(savedOrderDetail,is(equalTo(orderDetail)));
    }

    @Test
    public void shouldCallDeleteMethodOfOrderDetailRepository_whenDeleteIsCalled() throws Exception{
        doNothing().when(orderDetailRepository).delete(3);
        OrderDetailRepository odrepo = Mockito.mock(OrderDetailRepository.class);
        orderDetailServiceImpRepo.delete(3);
        verify(orderDetailRepository,times(1)).delete(3);
    }

    @Test
    public void shouldReturnListOrderDetail_whenListAllIsCalled() throws Exception{
        when(orderDetailRepository.findAll()).thenReturn(orderDetails);
        List<OrderDetail> orderDetailList = (List<OrderDetail>) orderDetailServiceImpRepo.listAll();
        assertThat(orderDetailList,is(equalTo(orderDetails)));
    }
}
