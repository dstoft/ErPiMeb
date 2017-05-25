/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.domain.commoditymanager;

import java.util.List;

/**
 *
 * @author AKT
 */
public class Category {
    
    private String name = "Madlavning";
    private List<String> subCategories;
    private List<String> tagList;
    private List<Product> productList;
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
}
