package com.Ecommerce.domain;

/**
 * Created by Ovi on 4/11/2017.
 */
public class OrderDetail {

    private Order order;
    private Product product;
    private int productQuantity;

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

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }
}
