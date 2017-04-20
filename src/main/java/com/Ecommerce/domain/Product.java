package com.Ecommerce.domain;

import java.math.BigDecimal;

/**
 * Created by Ovi on 4/11/2017.
 */
public class Product {
    private int id;
    private int version;
    private String description;
    private BigDecimal price;
    private String imageUrl;

    public void setId(int id) {
        this.id = id;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}



