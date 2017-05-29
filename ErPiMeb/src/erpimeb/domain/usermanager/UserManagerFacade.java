/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.domain.usermanager;

import erpimeb.domain.commoditymanager.Product;
import erpimeb.domain.ordermanager.Order;
import java.util.List;

/**
 *
 * @author chris
 */
public interface UserManagerFacade {
    
    public abstract List<Product> getCartProducts();
    public abstract Customer getCurrentCustomer();
    public abstract void saveCustomerChanges(String name, String email, String phoneNumber, String address, int zip, String country);
    public abstract boolean adminLogin(String username, String password);
    public abstract boolean userLogin(String username, String password);
    public abstract boolean createCustomer(String name, String password, String email, Address address, String phoneNumber);
    public abstract List<Order> getOrderHistory();
    
}
