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
public class Cart {
    
    private List<Integer> products;
    private double total;
    
    private Checkout currentCheckout;
    
    public void createCheckout() {
        currentCheckout = new Checkout();
        
        currentCheckout.setCheckoutContents(products);
        currentCheckout.setCheckoutPrice(total);
    }
    
    public Checkout getCheckout() {
        return currentCheckout;
    }
    
}
