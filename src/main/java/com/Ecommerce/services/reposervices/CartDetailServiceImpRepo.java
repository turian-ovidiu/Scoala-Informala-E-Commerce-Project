package com.Ecommerce.services.reposervices;

import com.Ecommerce.domain.CartDetail;
import com.Ecommerce.repositories.CartDetailRepository;
import com.Ecommerce.services.CartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ovi on 5/14/2017.
 */

@Service
@Transactional(readOnly = true)
public class CartDetailServiceImpRepo implements CartDetailService {

    private CartDetailRepository cartDetailRepository;

    @Autowired
    public void setCartDetailRepository(CartDetailRepository cartDetailRepository) {
        this.cartDetailRepository = cartDetailRepository;
    }

    @Override
    public List<?> listAll() {
        List<CartDetail> orders = new ArrayList<>();
        cartDetailRepository.findAll().forEach(orders::add);
        return orders;
    }

    @Override
    public CartDetail getById(Integer id) {
        return cartDetailRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = false)
    public CartDetail saveOrUpdate(CartDetail domainObject) {
        return cartDetailRepository.save(domainObject);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Integer id) {
        cartDetailRepository.delete(id);
    }

    @Override
    public List<CartDetail> findCartDetailsByCart_Id(int id) {
        List<CartDetail> cartDetails = new ArrayList<>();
        cartDetailRepository.findCartDetailsByCart_Id(id).forEach(cartDetails::add);
        return cartDetails;
    }
}
