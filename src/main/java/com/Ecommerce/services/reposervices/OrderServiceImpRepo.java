package com.Ecommerce.services.reposervices;

import com.Ecommerce.domain.*;
import com.Ecommerce.domain.enums.OrderStatus;
import com.Ecommerce.repositories.OrderRepository;
import com.Ecommerce.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ovi on 5/14/2017.
 */
public class OrderServiceImpRepo implements OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<?> listAll() {
        List<Order> orders = new ArrayList<>();
        orderRepository.findAll().forEach(orders::add);
        return orders;
    }

    @Override
    public Order getById(Integer id) {
        return orderRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = false)
    public Order saveOrUpdate(Order domainObject) {
        return orderRepository.save(domainObject);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Integer id) {
        orderRepository.delete(id);
    }

    @Override
    public Long countAll() {
        return orderRepository.count();
    }

    @Override
    public Double findAverage() {
        return orderRepository.findAverage();
    }

    @Override
    public List<Order> findOrdersByCustomer_IdOrderByDateCreatedDesc(int id) {
        List<Order> orders = new ArrayList<>();
        orderRepository.findOrdersByCustomer_IdOrderByDateCreatedDesc(id).forEach(orders::add);
        return orders;
    }

    @Override
    public Double findMax() {
        return orderRepository.findMax();
    }

    @Override
    public Double findAverageByCustomer(Customer customer) {
        return orderRepository.findAverageByCustomer(customer);
    }

    @Override
    public Double findMin() {
        return orderRepository.findMin();
    }

    @Override
    public List<Order> findOrdersByOrderStatus(OrderStatus orderStatus) {
        return orderRepository.findOrdersByOrderStatus(orderStatus);
    }

    @Override
    public Double findMinimumByCustomer(@Param("customer") Customer customer) {
        return orderRepository.findMinimumByCustomer(customer);
    }

    @Override
    public Double findMaximumByCustomer(@Param("customer") Customer customer) {
        return orderRepository.findMaximumByCustomer(customer);
    }

    @Override
    public Integer findCountByCustomer(@Param("customer") Customer customer) {
        return orderRepository.findCountByCustomer(customer);
    }

    @Override
    public List<Order> findOrdersByDateShippedContains(Date date) {
        return orderRepository.findOrdersByDateShippedContains(date);
    }

    @Override
    public Order finalizeOrder(User user, AddressShipping addressShipping) {
        Cart cart = new Cart();
        cart.setUser(user);

        Order order = new Order();
        order.setCustomer(user.getCustomer());
        order.setOrderStatus(OrderStatus.NEW);
        order.setShipToAddress(addressShipping);

        user.getCustomer().setShippingAddress(addressShipping);


        List<CartDetail> cartDetails = user.getCart().getCartDetails();
        for (CartDetail cartDetail : cartDetails) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setProduct(cartDetail.getProduct());
            orderDetail.setQuantity(cartDetail.getQuantity());
            order.addToOrderDetails(orderDetail);
            cart.removeCartDetails(cartDetail);
        }
        order.calculateTotalCost();
        return order;
    }
}
