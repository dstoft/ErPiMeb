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
public class WebshopController {
    
    private List<Customer> customers;
    private Customer currentCustomer;
    
    private Order currentOrder;
    
    public WebshopController() {
        // Initiate lists
        customers = new ArrayList<>();
    }
    
    public void goToCheckout() {
        currentOrder = currentCustomer.goToCheckout();
    }
    
    public void setOrderName(String name) {
        currentOrder.setName(name);
    }
    
    public void setOrderEmail(String email) {
        currentOrder.setEmail(email);
    }
    
    public void setTOSVerification(boolean checkedStatus) {
        
    }
    
    public void confirmAndInitiatePayment() {
        
    }
    
    public void cancelCheckout() {
        
    }
}
