/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.domain.ordermanager;

import erpimeb.domain.commoditymanager.Product;
import java.util.List;

/**
 *
 * @author AKT
 */
public class Order {
    
    private int id;
    private String name;
    private String email;
    private String phoneNumber;
    private long timeStamp;
    private String status;
    private boolean tempOrder;
    private int orderNumber;
    private List<Product> products;
    private double total;
    private String paymentMethod;
    private String deliveryInformation;
    private boolean tos;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public String getStatus() {
        return status;
    }

    public boolean isTempOrder() {
        return tempOrder;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getTotal() {
        return total;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getDeliveryInformation() {
        return deliveryInformation;
    }

    public boolean isTos() {
        return tos;
    }
    
}
