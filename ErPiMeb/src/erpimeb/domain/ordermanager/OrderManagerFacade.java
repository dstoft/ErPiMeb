/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.domain.ordermanager;

/**
 *
 * @author chris
 */
public interface OrderManagerFacade {
    
    public abstract void setOrderName(String name);
    public abstract void setOrderAddress(String address, int zip, String country);
    public abstract void setOrderEmail(String email);
    public abstract void setOrderTOS(boolean status);
    public abstract boolean confirmOrder();
    public abstract void cancelOrder();
    public abstract Order showSpecificOrder(int orderId);
    public abstract Order startCheckout();
    
}
