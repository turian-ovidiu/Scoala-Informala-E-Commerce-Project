package com.Ecommerce.repositories;

import com.Ecommerce.configuration.RepositoryConfig;
import com.Ecommerce.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Turian Ovidiu.
 * This class represent the customer repository test.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RepositoryConfig.class})
public class CustomerRepositoryTest {


    private CustomerRepository customerRepository;

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Test
    public void customerTest(){

        Customer customer = new Customer();
        customer.setFirstName("firstName");
        customer.setLastName("lastName");
        customer.setEmail("email@email.com");
        customer.setPhoneNumber("0743244432");

        assertNull(customer.getId());
        customerRepository.save(customer);
        assertNotNull(customer.getId());

        Customer fetchedCustomer = customerRepository.findOne(customer.getId());
        assertNotNull(fetchedCustomer);

        assertEquals(customer.getId(),fetchedCustomer.getId());
        assertEquals(customer.getFirstName(),fetchedCustomer.getFirstName());
        assertEquals(customer.getEmail(),fetchedCustomer.getEmail());

        fetchedCustomer.setLastName("lastNameUp");
        customerRepository.save(fetchedCustomer);

        Customer updateCustomer = customerRepository.findOne(fetchedCustomer.getId());
        assertEquals(fetchedCustomer.getLastName(),updateCustomer.getLastName());

        long customerCount = customerRepository.count();
        assertEquals(customerCount,1);

        Iterable<Customer> customers = customerRepository.findAll();
        int count = 0;

        for (Customer c: customers){
            count++;
        }

        assertEquals(count,1);

        customerRepository.delete(updateCustomer.getId());
        assertEquals(0,customerRepository.count());
    }
}
