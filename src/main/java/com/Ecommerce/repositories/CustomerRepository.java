package com.Ecommerce.repositories;

import com.Ecommerce.domain.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Turian Ovidiu.
 * This is the interface for Customer Repository.
 */
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    List<Customer> findAllByFirstNameContaining(String customerName);
    List<Customer> findAllByLastNameContaining(String customerLastName);


    @Override
    long count();
}
