package com.Ecommerce.services;

import com.Ecommerce.domain.Customer;

import java.util.List;

/**
 * Created by Turian Ovidiu.
 * This is the interface for CustomerService implementation.
 */
public interface CustomerService extends CRUDService<Customer>{

    List<Customer> findByFirstNameContaining(String customerName);
    List<Customer> findByLastNameContaining(String customerLastName);
    Long countAll();
}
