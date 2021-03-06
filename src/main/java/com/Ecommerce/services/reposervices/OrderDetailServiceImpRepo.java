package com.Ecommerce.services.reposervices;

import com.Ecommerce.domain.OrderDetail;
import com.Ecommerce.domain.Product;
import com.Ecommerce.repositories.OrderDetailRepository;
import com.Ecommerce.services.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Turian Ovidiu.
 * This class represent the implementation of OrderDetailService interface.
 */
@Service
@Transactional(readOnly = true)
public class OrderDetailServiceImpRepo implements OrderDetailService {

    private OrderDetailRepository orderDetailRepository;

    @Autowired
    public void setOrderDetailRepository(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public List<?> listAll() {
        List<OrderDetail> orders = new ArrayList<>();
        orderDetailRepository.findAll().forEach(orders::add);
        return orders;
    }

    @Override
    public OrderDetail getById(Integer id) {
        return orderDetailRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = false)
    public OrderDetail saveOrUpdate(OrderDetail domainObject) {
        return orderDetailRepository.save(domainObject);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Integer id) {
        orderDetailRepository.delete(id);
    }


    @Override
    public Integer findCountByProduct(@Param("product") Product product) {
        return orderDetailRepository.findCountByProduct(product);
    }
}
