package com.Ecommerce.repositories;

import com.Ecommerce.domain.Customer;
import com.Ecommerce.domain.Order;
import com.Ecommerce.domain.enums.OrderStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Turian Ovidiu.
 * This is the interface for Order Repository.
 */
public interface OrderRepository extends CrudRepository<Order,Integer> {

    @Override
    long count();

    @Query(value = "select AVG (o.totalCost) from Order o")
    Double findAverage();

    @Query(value = "select MAX (o.totalCost) from Order o")
    Double findMax();

    @Query(value = "select MIN (o.totalCost) from Order o")
    Double findMin();


    @Query(value = "select AVG (o.totalCost) from Order o where o.customer=:customer")
    Double findAverageByCustomer(@Param("customer") Customer customer);

    @Query(value = "select MIN (o.totalCost) from Order o where o.customer=:customer")
    Double findMinimumByCustomer(@Param("customer") Customer customer);

    @Query(value = "select MAX (o.totalCost) from Order o where o.customer=:customer")
    Double findMaximumByCustomer(@Param("customer") Customer customer);

    @Query(value = "select COUNT(o) from Order o where o.customer=:customer")
    Integer findCountByCustomer(@Param("customer") Customer customer);

    List<Order> findOrdersByCustomer_IdOrderByDateCreatedDesc(int id);

    List<Order> findOrdersByOrderStatus(OrderStatus orderStatus);

    List<Order> findOrdersByDateShippedContains(Date date);
}
