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
    
    List<Product> getProducts(){
        return this.products;
    }
    
    void addProduct(Product product){
        this.products.add(product);
        this.total += product.getPrice();
    }
    
    void removeProduct(Product product){
        while(this.products.contains(product)){
            this.removeOneProduct(product);
        }
    }
    
    void removeOneProduct(Product product){
        this.total -= product.getPrice();
        this.products.remove(product);
    }
    
    double getTotalPrice(){
        return this.total;
    }
}
