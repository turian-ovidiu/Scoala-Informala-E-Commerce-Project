package com.Ecommerce.repositories;

import com.Ecommerce.domain.CartDetail;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Ovi on 5/14/2017.
 */
public interface CartDetailRepository extends CrudRepository<CartDetail,Integer> {

    List<CartDetail> findCartDetailsByCart_Id(int id);
}
