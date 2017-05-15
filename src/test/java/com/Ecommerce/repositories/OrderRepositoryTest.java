package com.Ecommerce.repositories;

import com.Ecommerce.configuration.RepositoryConfig;
import com.Ecommerce.domain.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Ovi on 5/15/2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RepositoryConfig.class})
public class OrderRepositoryTest {


    private OrderRepository orderRepository;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Test
    public void orderTest(){

        Order order = new Order();
        order.setTotalCost(20.5);


        assertNull(order.getId());
        orderRepository.save(order);
        assertNotNull(order.getId());

        Order fetchedOrder = orderRepository.findOne(order.getId());
        assertNotNull(fetchedOrder);

        assertEquals(order.getId(),fetchedOrder.getId());
        assertEquals(order.getTotalCost(),fetchedOrder.getTotalCost());

        fetchedOrder.setTotalCost(32.43);
        orderRepository.save(fetchedOrder);

        Order updateOrder = orderRepository.findOne(fetchedOrder.getId());
        assertEquals(fetchedOrder.getTotalCost(),updateOrder.getTotalCost());

        long orderCount = orderRepository.count();
        assertEquals(orderCount,1);

        Iterable<Order> orders = orderRepository.findAll();

        int count = 0;

        for (Order o: orders){
            count++;
        }
        assertEquals(count,1);

        orderRepository.delete(updateOrder);
        assertEquals(0,orderRepository.count());

    }
}
