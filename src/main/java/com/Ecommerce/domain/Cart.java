package com.Ecommerce.domain;

import java.util.List;

/**
 * Created by Ovi on 4/11/2017.
 */
public class Cart {
    private int id;
    private int version;
    private User user;
    private List<CartDetail> cartDetails;

    public List<CartDetail> getCartDetails() {
        return cartDetails;
    }

    public void setCartDetails(List<CartDetail> cartDetails) {
        this.cartDetails = cartDetails;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public User getUser() {
        return user;
    }

}
