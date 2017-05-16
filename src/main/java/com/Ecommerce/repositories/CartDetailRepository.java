package com.Ecommerce.repositories;

import com.Ecommerce.domain.CartDetail;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Turian Ovidiu.
 * This is the interface for Cart Detail Repository.
 */
public interface CartDetailRepository extends CrudRepository<CartDetail,Integer> {

    List<CartDetail> findCartDetailsByCart_Id(int id);
}
