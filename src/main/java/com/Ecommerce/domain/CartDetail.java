package com.Ecommerce.domain;

import javax.persistence.*;

/**
 * Created by Ovi on 4/11/2017.
 */
@Entity
public class CartDetail implements DomainObject{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer quantity;

    @Version
    private Integer version;

    @ManyToOne
    private Cart cart;

    @ManyToOne
    private Product product;

    @Override
    public Integer getId() {
        return id;
    }

    public CartDetail() {
        setQuantity(1);
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
