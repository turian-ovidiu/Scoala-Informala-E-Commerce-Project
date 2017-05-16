package com.Ecommerce.services;

import com.Ecommerce.domain.OrderDetail;
import com.Ecommerce.domain.Product;
import org.springframework.data.repository.query.Param;

/**
 * Created by Turian Ovidiu.
 * This is the interface for OrderDetailService implementation.
 */
public interface OrderDetailService extends CRUDService<OrderDetail>{

    Integer findCountByProduct(@Param("product") Product product);
}
