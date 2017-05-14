package com.Ecommerce.services.reposervices;

import com.Ecommerce.domain.CartDetail;
import com.Ecommerce.domain.Product;
import com.Ecommerce.domain.User;
import com.Ecommerce.repositories.ProductRepository;
import com.Ecommerce.services.ProductService;
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

@Service
@Profile("springdatajpa")
@Transactional(readOnly = true)
public class ProductServiceImpRepo implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> listAll(){
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    @Override
    public Product getById(Integer id){
        return productRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = false)
    @RolesAllowed("ADMIN")
    public Product saveOrUpdate(Product domainObject){
        return productRepository.save(domainObject);
    }

    @Override
    @Transactional(readOnly = false)
    @RolesAllowed("ADMIN")
    public void delete(Integer id){
        productRepository.delete(id);
    }

    @Override
    public List<Product> findByDescription(String description) {
        List<Product> products = new ArrayList<>();
        productRepository.findAllByDescriptionContaining(description).forEach(products::add);
        return products;
    }


    @Override
    public List<Product> findByName(String name) {
        List<Product> products = new ArrayList<>();
        productRepository.findAllByNameContaining(name).forEach(products::add);
        return products;
    }

    @Override
    public Long countAll() {
        return productRepository.count();
    }

    @Override
    public void addProduct(User user, Product product) {
        CartDetail cartDetail = new CartDetail();
        cartDetail.setProduct(product);

        user.getCart().addCartDetail(cartDetail);
    }
}
