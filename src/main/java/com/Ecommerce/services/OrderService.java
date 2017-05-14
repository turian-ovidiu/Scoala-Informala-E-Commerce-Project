package com.Ecommerce.services;

import com.Ecommerce.domain.AddressShipping;
import com.Ecommerce.domain.Customer;
import com.Ecommerce.domain.Order;
import com.Ecommerce.domain.User;
import com.Ecommerce.domain.enums.OrderStatus;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Ovi on 5/14/2017.
 */
public interface OrderService extends CRUDService<Order>{

    Long countAll();
    Double findAverage();
    Double findMax();
    Double findMin();

    List<Order> findOrdersByCustomer_IdOrderByDateCreatedDesc(int id);

    List<Order> findOrdersByOrderStatus(OrderStatus orderStatus);

    Double findAverageByCustomer(@Param("customer")Customer customer);

    Double findMinimumByCustomer(@Param("customer") Customer customer);

    Double findMaximumByCustomer(@Param("customer") Customer customer);

    Integer findCountByCustomer(@Param("customer") Customer customer);

    List<Order> findOrdersByDateShippedContains(Date date);

    Order finalizeOrder(User user, AddressShipping addressShipping);
}
