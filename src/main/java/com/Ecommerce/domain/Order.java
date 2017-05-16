package com.Ecommerce.domain;

import com.Ecommerce.domain.enums.OrderStatus;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Turian Ovidiu.
 * This class represent Order Entity.
 */
@Entity
@Table(name = "ORDER_HEADER")
public class Order extends AbstractDomain{

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Embedded
    private AddressShipping shipToAddress;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", orphanRemoval = true)
    private List<OrderDetail> orderDetails = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;


    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private Date dateShipped;

    private Double totalCost = 0.0;


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public AddressShipping getShipToAddress() {
        return shipToAddress;
    }

    public void setShipToAddress(AddressShipping shipToAddress) {
        this.shipToAddress = shipToAddress;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public void addToOrderDetails(OrderDetail orderDetail) {
        orderDetail.setOrder(this);
        orderDetails.add(orderDetail);
    }

    public void removeOrderDetail(OrderDetail orderDetail) {
        orderDetail.setOrder(null);
        orderDetails.remove(orderDetail);
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getDateShipped() {
        return dateShipped;
    }

    public void setDateShipped(Date dateShipped) {
        this.dateShipped = dateShipped;
    }

    @PreUpdate
    @PrePersist
    public void updateDateShipped() {
        if (dateShipped == null) {
            dateShipped = new Date();
        }
    }

    public void calculateTotalCost() {
        if (getOrderDetails().isEmpty()){
            totalCost = 0.0;
        }
        if (!getOrderDetails().isEmpty()){
            for (OrderDetail orderDetail: getOrderDetails()) {
                totalCost += orderDetail.getProduct().getPrice() * orderDetail.getQuantity();
            }
        }
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }
}
