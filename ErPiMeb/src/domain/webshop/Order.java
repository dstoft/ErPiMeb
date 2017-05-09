/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.webshop;

import java.util.List;

/**
 *
 * @author AKT
 */
public class Order {
    
    private boolean tempOrder;
    private int orderNumber;
    private List<Integer> products;
    private double total;
    private String paymentMethod;
    private String name;
    private String email;
    private long timeStamp;
    
    public int getOrderNumber() {
        return orderNumber;
    }
    
    public double getTotal() {
        return total;
    }
    
    public long getTimestamp() {
        return timeStamp;
    }
}
