package com.Ecommerce.domain;

/**
 * Created by Ovi on 4/11/2017.
 */
public class CartDetail {
    private int id;
    private int version;
    private int quantity;
    private Cart cart;
    private Product product;

    public void setId(int id) {
        this.id = id;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public int getQuantity() {
        return quantity;
    }

    public Cart getCart() {
        return cart;
    }

    public Product getProduct() {
        return product;
    }
}
