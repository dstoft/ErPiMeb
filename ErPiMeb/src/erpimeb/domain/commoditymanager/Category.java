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
    
    private String name;
    private List<String> subCategories;
    private List<String> tagList;
    private List<Product> productList;

    public Category(String name, List<String> subCategories, List<String> tagList, List<Product> productList) {
	this.name = name;
	this.subCategories = subCategories;
	this.tagList = tagList;
	this.productList = productList;
    }
    
    
}
