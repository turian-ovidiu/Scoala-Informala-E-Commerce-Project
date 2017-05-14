package com.Ecommerce.services.reposervices;

import com.Ecommerce.domain.Customer;
import com.Ecommerce.repositories.CustomerRepository;
import com.Ecommerce.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ovi on 5/14/2017.
 */

@Transactional(readOnly = true)
@Service
@Profile("springdatajpa")
public class CustomerServiceImpRepo implements CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @RolesAllowed("ADMIN")
    public List<?> listAll(){
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customers::add);
        return customers;
    }

    @Override
    public Customer getById(Integer id){
        return customerRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = false)
    public Customer saveOrUpdate(Customer domainObject){
        return customerRepository.save(domainObject);
    }

    @Override
    @Transactional(readOnly = false)
    @RolesAllowed("ADMIN")
    public void delete(Integer id){
        customerRepository.delete(id);
    }


    @Override
    @RolesAllowed("ADMIN")
    public List<Customer> findByFirstNameContaining(String customerName) {
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAllByFirstNameContaining(customerName).forEach(customers::add);
        return customers;
    }

    @Override
    @RolesAllowed("ADMIN")
    public List<Customer> findByLastNameContaining(String customerLastName) {
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAllByLastNameContaining(customerLastName).forEach(customers::add);
        return customers;
    }

    @Override
    public Long countAll() {
        return customerRepository.count();
    }
}
