/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.domain.ordermanager;

import erpimeb.domain.commoditymanager.Product;
import erpimeb.domain.usermanager.Address;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AKT
 */
public class Order {
    
    private String name;
    private String email;
    private String phoneNumber;
    private long timeStamp;
    private String status;
    private int orderNumber;
    private List<Product> products;
    private double total;
    private String paymentMethod;
    private Address address;
    private boolean tos;

    public long getTimeStamp() {
        return timeStamp;
    }

    public int getOrderNumber() {
        return orderNumber;
    }
    
    public Order(){
        this.products = new ArrayList<>();
        this.setStatus("In Progress");
        this.timeStamp = System.currentTimeMillis();
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getStatus() {
        return status;
    }

    public double getTotal() {
        return total;
    }

    public Address getAddress() {
        return address;
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

    public List<Product> getProducts() {
        return products;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public boolean isTos() {
        return tos;
    }
    
    void setPaymentMethod(String method) {
        this.paymentMethod = method;
    }
}
