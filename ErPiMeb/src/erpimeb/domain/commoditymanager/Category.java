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
    
    public Category(String name, List<Category> subcategories, List<String> tagList, List<Product> productList) {
	this();
	this.name = name;
	this.subcategories.addAll(subcategories);
	this.tagList = tagList;
	this.productList = productList;
    }

    public Category() {
	this.subcategories = new ArrayList<>();
	this.tagList = new ArrayList<>();
	this.productList = new ArrayList<>();
    }
    
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
	return name;
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

    public List<Category> getSubcategories() {
	return subcategories;
    }

    public List<String> getTagList() {
	return tagList;
    }

    public List<Product> getProductList() {
	return productList;
    }
    
    
}
