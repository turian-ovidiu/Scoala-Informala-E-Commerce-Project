package com.Ecommerce.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by Turian Ovidiu.
 * This class represent OrderDetail Entity.
 */
@Entity
public class OrderDetail extends AbstractDomain{

    @ManyToOne
    private Order order;

    @ManyToOne
    private Product product;

    private Integer quantity;


    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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
