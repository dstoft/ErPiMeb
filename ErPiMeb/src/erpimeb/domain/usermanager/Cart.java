/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.domain.usermanager;

import erpimeb.domain.commoditymanager.Product;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AKT
 */
public class Cart {
    
    private List<Product> products;
    private double total;
    
    public Cart(){
        this.products = new ArrayList<>();
    }
    
    public List<Product> getProducts(){
        return this.products;
    }
    
    public void addProduct(Product product){
        this.products.add(product);
        this.total += product.getPrice();
    }
    
    public void removeProduct(Product product){
        while(this.products.contains(product)){
            this.removeOneProduct(product);
        }
    }
    
    public void removeOneProduct(Product product){
        this.total -= product.getPrice();
        this.products.remove(product);
    }
    
    public double getTotalPrice(){
        return this.total;
    }
}
