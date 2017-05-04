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
    private Address address;
    
    public void setAddress(Address orderAddress) {
        address = orderAddress;
    }
    
    public void setName (String name) {
        this.name = name;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public boolean isRequiredInformationFIlled() {
        boolean isFilled = false;
        if (address.getAddress() == null || address.getZip() == 0 || address.getCountry() == null) {
            return isFilled;
        } else {
            isFilled = true;
            return isFilled;
        }
        
    }
    
    
}
