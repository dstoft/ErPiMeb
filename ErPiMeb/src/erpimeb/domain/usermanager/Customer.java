/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.domain.usermanager;

import erpimeb.domain.ordermanager.Order;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AKT
 */
public class Customer {
    
    private boolean tempCustomer;
    private String name;
    private Address address;
    private String phoneNumber;
    private String email;
    private String password;
    private List<Order> orders;
    
    public Customer() {
        this.orders = new ArrayList<>();
    }

    public Customer(String name, Address address, String phoneNumber, String email, String password) {
	this.name = name;
	this.address = address;
	this.phoneNumber = phoneNumber;
	this.email = email;
	this.password = password;
    }
    
    public void addAddress(Address newAddress) {
        address = newAddress;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(Address address) {
	this.address = address;
    }

    public void setPassword(String password) {
	this.password = password;
    }
    
    public void setUserInfo(String name, String email, String password, String phoneNumber, Address address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    /**
     * @return the tempCustomer
     */
    public boolean isTempCustomer() {
        return tempCustomer;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    public List<Order> getOrders() {
        return this.orders;
    }
    
}
