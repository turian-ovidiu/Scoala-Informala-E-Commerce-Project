package com.Ecommerce.repositories;

import com.Ecommerce.domain.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Ovi on 4/11/2017.
 */
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    List<Customer> findAllByFirstNameContaining(String customerName);
    List<Customer> findAllByLastNameContaining(String customerLastName);


    @Override
    long count();
}
