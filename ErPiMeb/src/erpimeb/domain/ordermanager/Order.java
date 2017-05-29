/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.domain.ordermanager;

import erpimeb.domain.commoditymanager.Product;
import erpimeb.domain.usermanager.Address;
import java.util.List;

/**
 *
 * @author AKT
 */
public class Order {
    
    private int id;
    private String name;
    private String email;
    private String phoneNumber;
    private long timeStamp;
    private String status;
    private boolean tempOrder;
    private int orderNumber;
    private List<Product> products;
    private double total;
    private String paymentMethod;
    private String deliveryInformation;
    private Address address;
    private boolean tos;
    
    public Order(){
        this.status = "In Progress";
    }
    
    protected void setStatus(String status){
        this.status = status;
    }
    
    protected void addProducts(List<Product> products){
        this.products = products;
        this.calculateTotal();
    }
    
    private void calculateTotal(){
        for(Product prod : this.products){
            this.total += prod.getPrice();
        }
    }
    
    public double getTotalPrice(){
        return this.total;
    }
    
    protected void setName(String name){
        this.name = name;
    }
    
    protected void setEmail(String email){
        this.email = email;
    }
    
    protected void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    
    protected void setAddress(Address address){
        this.address = address;
    }
    
    public void setAddress(String address, int zip, String country){
        this.setAddress(new Address(address, zip, country));
    }
    
    protected void setTos(boolean accept){
        this.tos = accept;
    }
    
    public String getEmail(){
        return this.email;
    }
    
    protected boolean isRequiredInformationFilled(){
        if(this.name == null || this.name.isEmpty()){
            return false;
        }
        if(this.email == null || this.email.isEmpty()){
            return false;
        }
        if(this.phoneNumber == null || this.phoneNumber.isEmpty()){
            return false;
        }
        if(this.address.getCountry() == null || this.address.getCountry().isEmpty()){
            return false;
        }
        if(this.address.getZip() == 0){
            return false;
        }
        if(this.address.getAddress() == null || this.address.getAddress().isEmpty()){
            return false;
        }
        return true;
    }
    
    protected boolean isTosVerified(){
        return this.tos;
    }
}
