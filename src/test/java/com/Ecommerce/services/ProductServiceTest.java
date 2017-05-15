package com.Ecommerce.services;

import com.Ecommerce.domain.Product;
import com.Ecommerce.repositories.ProductRepository;
import com.Ecommerce.services.reposervices.ProductServiceImpRepo;
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
 * Created by Ovi on 5/15/2017.
 */
public class ProductServiceTest {


    private ProductServiceImpRepo productServiceImpRepo;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private Product product;

    @Mock
    private List<Product> products;


    @Before
    public void setupMock(){
        MockitoAnnotations.initMocks(this);
        productServiceImpRepo = new ProductServiceImpRepo();
        productServiceImpRepo.setProductRepository(productRepository);
        Product prod = new Product();
        prod.setName("Burger California");
        prod.setDescription("tomatoes,onions");

        products = new ArrayList<>();
        products.add(prod);

    }

    @Test
    public void shouldReturnProduct_whenGetByIdIsCalled() throws Exception{
        when(productRepository.findOne(2)).thenReturn(product);
        Product retrievedProduct = productServiceImpRepo.getById(2);
        assertThat(retrievedProduct,is(equalTo(product)));
    }

    @Test
    public void shouldReturnProduct_whenSaveOrUpdateIsCalled() throws Exception{
        when(productRepository.save(product)).thenReturn(product);
        Product savedProduct = productServiceImpRepo.saveOrUpdate(product);
        assertThat(savedProduct,is(equalTo(product)));
    }

    @Test
    public void shouldCallDeleteMethodOfProductRepository_whenDeleteIsCalled() throws Exception{
        doNothing().when(productRepository).delete(2);
        ProductRepository prepo = Mockito.mock(ProductRepository.class);
        productServiceImpRepo.delete(2);
        verify(productRepository,times(1)).delete(2);
    }

    @Test
    public void shouldReturnListProducts_whenListAllIsCalled() throws Exception{
        when(productRepository.findAll()).thenReturn(products);
        List<Product> retrievedListProducts = productServiceImpRepo.listAll();
        assertThat(retrievedListProducts,is(equalTo(products)));
    }

    @Test
    public void shouldReturnListProducts_whenFindByNameIsCalled() throws Exception{
        when(productRepository.findAllByNameContaining("Burger California")).thenReturn(products);
        List<Product> retrievedListProducts = productServiceImpRepo.findByName("Burger California");
        assertThat(retrievedListProducts,is(equalTo(products)));
    }

    @Test
    public void shouldReturnListProducts_whenFindByDescriptionIsCalled() throws Exception{
        when(productRepository.findAllByDescriptionContaining("tomatoes,onions")).thenReturn(products);
        List<Product> retrievedListProducts = productServiceImpRepo.findByDescription("tomatoes,onions");
        assertThat(retrievedListProducts,is(equalTo(products)));
    }
}
