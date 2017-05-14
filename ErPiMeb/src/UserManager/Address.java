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
 * @author chris
 */
public class Address {
    private String address;
    private Integer zip;
    private String country;
    private List<Customer> customers;
    private List<Order> orders;
    
    public Address(String address, Integer zip, String country) {
        this.address = address;
        this.zip = zip;
        this.country = country;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return the zip
     */
    public int getZip() {
        return zip;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }
}
