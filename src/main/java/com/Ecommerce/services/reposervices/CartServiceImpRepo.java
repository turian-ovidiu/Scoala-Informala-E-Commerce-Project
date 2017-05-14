package com.Ecommerce.services.reposervices;

import com.Ecommerce.domain.Cart;
import com.Ecommerce.repositories.CartRepository;
import com.Ecommerce.services.CartService;
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
@Profile("springdatajpa")
@Transactional(readOnly = true)
public class CartServiceImpRepo implements CartService {

    private CartRepository cartRepository;


    @Autowired
    public void setCartRepository(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public List<?> listAll() {
        List<Cart> orders = new ArrayList<>();
        cartRepository.findAll().forEach(orders::add);
        return orders;
    }

    @Override
    public Cart getById(Integer id) {
        return cartRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = false)
    public Cart saveOrUpdate(Cart domainObject) {
        return cartRepository.save(domainObject);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Integer id) {
        cartRepository.delete(id);
    }
}
