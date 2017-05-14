package com.Ecommerce.services;

import com.Ecommerce.domain.CartDetail;

import java.util.List;

/**
 * Created by Ovi on 5/14/2017.
 */
public interface CartDetailService extends CRUDService<CartDetail>{

    List<CartDetail> findCartDetailsByCart_Id(int id);
}
