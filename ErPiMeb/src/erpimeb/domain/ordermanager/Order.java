/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.domain.ordermanager;

import erpimeb.domain.commoditymanager.Category;
import erpimeb.domain.commoditymanager.Product;
import java.util.ArrayList;
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

    public Order(int id, String name, String email, String phoneNumber, long timeStamp, String status, boolean tempOrder, int orderNumber, List<Product> products, double total, String paymentMethod, String deliveryInformation, boolean tos) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.timeStamp = timeStamp;
        this.status = status;
        this.tempOrder = tempOrder;
        this.orderNumber = orderNumber;
        this.products = products;
        this.total = total;
        this.paymentMethod = paymentMethod;
        this.deliveryInformation = deliveryInformation;
        this.tos = tos;
    }
    
}
