package com.backend.E_Commerce.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer orderId;

    private Integer ordersetId;
    private Integer productId;
    private Integer quantity;
    private Float price;
    private Float orderValue;

    Orders() {
    }

    Orders(Integer productId, Integer quantity, Float price, Float orderValue) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.orderValue = orderValue;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public Integer getOrdersetId() {
        return ordersetId;
    }

    public Integer getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Float getPrice() {
        return price;
    }

    public Float getOrderValue() {
        return orderValue;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public void setOrdersetId(Integer ordersetId) {
        this.ordersetId = ordersetId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setOrderValue(Float orderValue) {
        this.orderValue = orderValue;
    }

    @Override
    public String toString() {
        String res = "";
        res += "\t\t" + this.getProductId() + "\t\t" + this.getPrice() + "\t\t" + this.getQuantity() + "\t\t"
                + this.getOrderValue() + "\t\t";
        return res;
    }
}
