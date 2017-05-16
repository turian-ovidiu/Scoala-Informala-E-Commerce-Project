package com.Ecommerce.repositories;

import com.Ecommerce.domain.OrderDetail;
import com.Ecommerce.domain.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by Turian Ovidiu.
 * This is the interface for Order Detail Repository.
 */
public interface OrderDetailRepository extends CrudRepository<OrderDetail,Integer> {


    @Query(value = "select COUNT(od) from OrderDetail od where od.product=:product")
    Integer findCountByProduct(@Param("product") Product product);
}
