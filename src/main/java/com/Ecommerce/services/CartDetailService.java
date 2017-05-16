package com.Ecommerce.services;

import com.Ecommerce.domain.CartDetail;

import java.util.List;

/**
 * Created by Turian Ovidiu.
 * This is the interface for CartDetailService implementation.
 */
public interface CartDetailService extends CRUDService<CartDetail>{

    List<CartDetail> findCartDetailsByCart_Id(int id);
}
