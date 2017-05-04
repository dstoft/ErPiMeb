/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.webshop;

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
    
    private Cart currentCart;
    
    private Order currentOrder;
    private Address orderAddress;
    
    public Order goToCheckout() {
        currentCart.createCheckout();
        currentOrder = new Order();
        orderAddress = new Address();
        
        orderAddress.createCopyOf(address);
        currentOrder.setAddress(orderAddress);
        
        return currentOrder;
    }
    
    public Checkout getCheckout() {
        return currentCart.getCheckout();
    }
}
