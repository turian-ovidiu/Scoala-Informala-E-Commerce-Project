package com.Ecommerce.domain;

import com.Ecommerce.domain.enums.OrderStatus;

import java.util.Date;
import java.util.List;

/**
 * Created by Ovi on 4/11/2017.
 */
public class Order {
    private Customer customer;
    private AddressShipping addressShipping;
    private Date shipingDate;
    private List<OrderDetail> orderDetails;
    private OrderStatus orderStatus;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public AddressShipping getAddressShipping() {
        return addressShipping;
    }

    public void setAddressShipping(AddressShipping addressShipping) {
        this.addressShipping = addressShipping;
    }

    public Date getShipingDate() {
        return shipingDate;
    }

    public void setShipingDate(Date shipingDate) {
        this.shipingDate = shipingDate;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
