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
    private int orderID;
    private List<Product> products;
    private double total;
    private String paymentMethod;
    private Address address;
    private boolean tos;
    
    public Order(){
        this.products = new ArrayList<>();
        this.status = "In Progress";
        this.paymentMethod = "Incomplete";
        this.timeStamp = System.currentTimeMillis();
    }

    /* Getters begin */
    public long getTimeStamp() {
        return timeStamp;
    }

    public int getOrderID() {
        return orderID;
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
    
    public String getEmail(){
        return this.email;
    }
    
    boolean isTosVerified(){
        return this.tos;
    }

    public List<Product> getProducts() {
        return products;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
    /* Getters end */
    
    /* Setters begin */
    public void setOrderId(int id) {
        this.orderID = id;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    
    public void setAddress(Address address){
        this.address = address;
    }
    
    void setAddress(String address, int zip, String country){
        this.setAddress(new Address(address, zip, country));
    }
    
    void setTos(boolean accept){
        this.tos = accept;
    }
    
    public void setStatus(String status){
        this.status = status;
    }
    
    public void setPaymentMethod(String method) {
        this.paymentMethod = method;
    }
    
    public void setTotal(double total) {
        this.total = total;
    }
    
    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
    /* Setters end */
    
    /* Behavioural methods begin */
    public void addProducts(List<Product> products){
        this.products = products;
        this.calculateTotal();
    }
    
    private void calculateTotal(){
        for(Product prod : this.products){
            this.total += prod.getPrice();
        }
    }
    
    boolean isRequiredInformationFilled(){
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
    /* Behavioural methods end */
}
