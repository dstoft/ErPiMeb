/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.webshop;

import java.util.*;

/**
 *
 * @author AKT
 */
public class Customer {
    
    private boolean tempCustomer;
    private String name;
    private String password;
    private String address;
    private String country;
    private String phoneNumber;
    private int zip;
    
    private Set<Order> ordersMade;
    
    public List<Integer> getOrderHistoryAsList() {
        List<Integer> orderHistory = new ArrayList<>();
        
        for(Order o : ordersMade) {
            orderHistory.add(o.getOrderNumber());
        }
        
        return orderHistory;
    }
    
    public Order getSpecificOrderInformation(int orderNumber) {
        for (Order o : ordersMade) {
            if (o.getOrderNumber() == orderNumber) {
                return o;
            }
        }
        
        return null;
    }
    
    public void createReturnCase(int orderNumber, String returnKind) {
        ReturnCase rc = new ReturnCase(orderNumber, returnKind);
    }
}
