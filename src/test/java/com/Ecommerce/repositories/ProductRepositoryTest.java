package com.Ecommerce.repositories;

import com.Ecommerce.configuration.RepositoryConfig;
import com.Ecommerce.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Ovi on 5/15/2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RepositoryConfig.class})
public class ProductRepositoryTest {


    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Test
    public void productTest(){

        Product product = new Product();
        product.setName("Burger California");
        product.setDescription("Irish beef, cheddar, secret sauce, onions, tomatoes");
        product.setPrice(new Double(14.32));
        product.setImageUrl("californiaburger.jpg");



        assertNull(product.getId());
        productRepository.save(product);
        assertNotNull(product.getId());

        Product fetchedProduct = productRepository.findOne(product.getId());
        assertNotNull(fetchedProduct);

        assertEquals(product.getId(),fetchedProduct.getId());
        assertEquals(product.getDescription(),fetchedProduct.getDescription());

        //Update
        fetchedProduct.setDescription("update description");
        productRepository.save(fetchedProduct);

        Product updateProduct = productRepository.findOne(fetchedProduct.getId());
        assertEquals(fetchedProduct.getDescription(),updateProduct.getDescription());

        //Count
        long productCount = productRepository.count();
        assertEquals(productCount,1);

        Iterable<Product> products = productRepository.findAll();

        int count = 0;

        for (Product p: products){
            count++;
        }

        assertEquals(count,1);

        productRepository.delete(updateProduct.getId());
        assertEquals(0,productRepository.count());
    }
}
