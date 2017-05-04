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
public class Address {
    
    private String address;
    private String country;
    private int zip;
    
    public void createCopyOf(String address) {
        this.address = address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setZip(int zip) {
        this.zip = zip;
    }
    
    public int getZip() {
        return zip;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public String getCountry() {
        return country;
    }
}
