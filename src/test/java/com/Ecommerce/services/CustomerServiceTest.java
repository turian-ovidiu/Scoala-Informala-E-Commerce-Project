package com.Ecommerce.services;

import com.Ecommerce.domain.Customer;
import com.Ecommerce.repositories.CustomerRepository;
import com.Ecommerce.services.reposervices.CustomerServiceImpRepo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Turian Ovidiu.
 * This class represent the Customer service unit tests.
 */
public class CustomerServiceTest {

    private CustomerServiceImpRepo customerServiceImpRepo;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private Customer customer;

    @Mock
    List<Customer> customers;

    @Before
    public void setupMock(){
        MockitoAnnotations.initMocks(this);
        customerServiceImpRepo = new CustomerServiceImpRepo();
        customerServiceImpRepo.setCustomerRepository(customerRepository);
        Customer customerTest = new Customer();
        customerTest.setFirstName("John");
        customerTest.setLastName("Ben");
        customers = new ArrayList<>();
        customers.add(customerTest);
    }

    @Test
    public void shouldReturnCustomer_whenGetByIdIsCalled() throws  Exception{
        when(customerRepository.findOne(5)).thenReturn(customer);
        Customer retrievedCustomer = customerServiceImpRepo.getById(5);
        assertThat(retrievedCustomer,is(equalTo(customer)));
    }

    @Test
    public void shouldReturnCustomer_whenSaveOrUpdateIsCalled() throws Exception{
        when(customerRepository.save(customer)).thenReturn(customer);
        Customer savedCustomer = customerServiceImpRepo.saveOrUpdate(customer);
        assertThat(savedCustomer,is(equalTo(customer)));
    }

    @Test
    public void shouldCallDeleteMethodOfCustomerRepository_whenDeleteIsCalled() throws Exception{
        doNothing().when(customerRepository).delete(5);
        CustomerRepository crepo = Mockito.mock(CustomerRepository.class);
        customerServiceImpRepo.delete(5);
        verify(customerRepository,times(1)).delete(5);
    }

    @Test
    public void shouldReturnListCustomers_whenListAllIsCalled(){
        when(customerRepository.findAll()).thenReturn(customers);
        List<Customer> customerList = (List<Customer>) customerServiceImpRepo.listAll();
        assertThat(customerList,is(equalTo(customers)));
    }

    @Test
    public void shouldReturnListCustomers_whenFindByFirstNameContainingIsCalled(){
        when(customerRepository.findAllByFirstNameContaining("John")).thenReturn(customers);
        List<Customer> customerList = customerServiceImpRepo.findByFirstNameContaining("John");
        assertThat(customerList,is(equalTo(customers)));
    }

    @Test
    public void shouldReturnListCustomers_whenFindByLastNameContainingIsCalled(){
        when(customerRepository.findAllByLastNameContaining("Ben")).thenReturn(customers);
        List<Customer> customerList = customerServiceImpRepo.findByLastNameContaining("Ben");
        assertThat(customerList,is(equalTo(customers)));
    }
}
