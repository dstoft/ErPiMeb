/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.domain.commoditymanager;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AKT
 */
public class Category {
    
    private String name;
    private List<Category> subcategories;
    private List<String> tagList;
    private List<Product> productList;
    
    public Category() {
        this.subcategories = new ArrayList<>();
        this.tagList = new ArrayList<>();
        this.productList = new ArrayList<>();
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void addSubcategory(Category category) {
        this.subcategories.add(category);
    }
    
    public void addTag(String tagName) {
        this.tagList.add(name);
    }
    
    public void addProduct(Product product) {
        this.productList.add(product);
    }
    
}
