package com.Ecommerce.services;

import com.Ecommerce.domain.Customer;

import java.util.List;

/**
 * Created by Ovi on 5/14/2017.
 */
public interface CustomerService extends CRUDService<Customer>{

    List<Customer> findByFirstNameContaining(String customerName);
    List<Customer> findByLastNameContaining(String customerLastName);
    Long countAll();
}
