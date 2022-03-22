package com.backend.E_Commerce.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Ordersets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer ordersetId;

    private Integer userId;
    @OneToMany(targetEntity = Orders.class, cascade = CascadeType.ALL, mappedBy = "ordersetId")
    private List<Orders> orders;
    private Float ordersetValue;

    @OneToOne(cascade = CascadeType.ALL, targetEntity = ShipmentStatus.class)
    private ShipmentStatus shipmentStatusId;

    @OneToOne(cascade = CascadeType.ALL, targetEntity = Addresses.class)
    private Addresses shipmentAddress;
    private String invoiceUrl;

    public Ordersets(){}

    public Ordersets(List<Orders> orders, Float ordersetValue, ShipmentStatus shipmentStatusId, Addresses shipmentAddress, String invoiceUrl){
        this.orders = orders;
        this.ordersetValue = ordersetValue;
        this.shipmentStatusId = shipmentStatusId;
        this.shipmentAddress = shipmentAddress;
        this.invoiceUrl = invoiceUrl;
    }

    public Integer getOrdersetId() {
        return ordersetId;
    }
    public List<Orders> getOrders() {
        return orders;
    }
    public Float getOrdersetValue() {
        return ordersetValue;
    }
    public ShipmentStatus getShipmentStatusId() {
        return shipmentStatusId;
    }
    public Addresses getShipmentAddress() {
        return shipmentAddress;
    }
    public String getInvoiceUrl() {
        return invoiceUrl;
    }
    public Integer getUser_id() {
        return userId;
    }


    public void setOrdersetId(Integer ordersetId) {
        this.ordersetId = ordersetId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }
    public void setOrdersetValue(Float ordersetValue) {
        this.ordersetValue = ordersetValue;
    }
    public void setShipmentStatusId(ShipmentStatus shipmentStatusId) {
        this.shipmentStatusId = shipmentStatusId;
    }
    public void setShipmentAddress(Addresses shipmentAddress) {
        this.shipmentAddress = shipmentAddress;
    }
    public void setInvoiceUrl(String invoiceUrl) {
        this.invoiceUrl = invoiceUrl;
    }

    @Override
    public String toString() {
        String res = "";
        List<Orders> arr = this.getOrders();
        res += "**********************************************************************\n";
        res += "|\t\t Product id \t\t Price \t\t Quantity \t\t Value \t\t|\n";
        res += "**********************************************************************\n";
        for(Orders order : arr){
            res += "**********************************************************************\n";
            res += "|\t\t" + order.toString() + "\t\t|\n";            
            res += "**********************************************************************\n";
        }        
        return res;
    }
}
