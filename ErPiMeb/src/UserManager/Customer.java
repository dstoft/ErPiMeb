/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserManager;

import OrderManager.Order;
import java.util.List;

/**
 *
 * @author AKT
 */
public class Customer {
    
    private boolean tempCustomer;
    private String name;
    private Address address;
    private String country;
    private String phoneNumber;
    private int zip;
    private String email;
    private List<Order> orders;
    
}
