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
    
}
