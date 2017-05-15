package com.Ecommerce.services;

import com.Ecommerce.domain.Order;
import com.Ecommerce.repositories.OrderRepository;
import com.Ecommerce.services.reposervices.OrderServiceImpRepo;
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
 * Created by Ovi on 5/15/2017.
 */
public class OrderServiceTest {

    private OrderServiceImpRepo orderServiceImpRepo;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private Order order;

    @Mock
    private List<Order> orders;

    @Before
    public void setupMock(){
        MockitoAnnotations.initMocks(this);
        orderServiceImpRepo = new OrderServiceImpRepo();
        orderServiceImpRepo.setOrderRepository(orderRepository);
        orders = new ArrayList<>();
    }

    @Test
    public void shouldReturnOrder_whenGetByIdIsCalled() throws Exception{
        when(orderRepository.findOne(1)).thenReturn(order);
        Order retrievedOrder = orderServiceImpRepo.getById(1);
        assertThat(retrievedOrder,is(equalTo(order)));
    }

    @Test
    public void shouldReturnOrder_whenSaveOrUpdateIsCalled() throws Exception{
        when(orderRepository.save(order)).thenReturn(order);
        Order savedOrder = orderServiceImpRepo.saveOrUpdate(order);
        assertThat(savedOrder,is(equalTo(order)));
    }

    @Test
    public void shouldCallDeleteMethodOfOrderRepository_whenDeleteIsCalled() throws Exception{
        doNothing().when(orderRepository).delete(1);
        OrderRepository orepo = Mockito.mock(OrderRepository.class);
        orderServiceImpRepo.delete(1);
        verify(orderRepository,times(1)).delete(1);
    }

    @Test
    public void shouldReturnListOrder_whenListAllIsCalled(){
        when(orderRepository.findAll()).thenReturn(orders);
        List<Order> orderList = (List<Order>) orderServiceImpRepo.listAll();
        assertThat(orderList,is(equalTo(orders)));
    }
}
