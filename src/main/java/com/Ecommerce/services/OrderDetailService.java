package com.Ecommerce.services;

import com.Ecommerce.domain.OrderDetail;
import com.Ecommerce.domain.Product;
import org.springframework.data.repository.query.Param;

/**
 * Created by Ovi on 5/14/2017.
 */
public interface OrderDetailService extends CRUDService<OrderDetail>{

    Integer findCountByProduct(@Param("product") Product product);
}
