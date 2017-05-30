/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.domain.commoditymanager;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author chris
 */
public interface CommodityManagerFacade {
    
    public abstract void setSearchTerm(String searchTerm);
    public abstract String getSearchTerm();
    
    // ***** Product *****
    public abstract void fillProduct(Product product);
    public abstract boolean createProduct(String name, List<String> images, List<String> videoLinks, String description, HashMap<String, String> specifications, int erpSn, Category pickedCategory);
    public abstract void setCurrentProduct(Product product);
    public abstract Product getCurrentProduct();
    public abstract boolean saveChangesToProduct();
    public abstract List<Product> searchForProduct(String productName);
    // ***** Product END *****
    
    // ***** Category *****
    public abstract boolean createCategory(String name, List<Category> subcategories, List<String> tagList, List<Product> productList);
    public abstract void setCurrentCategory(Category category);
    public abstract Category getCurrentCategory();
    public abstract List<Category> getMainCategories();
    public abstract List<Category> getSubCategories(Category mainCategory);
    public abstract List<Category> getAllCategories();
    public abstract List<Category> getNonMainCategories();
    // ***** Category END *****
    
    public abstract List<String> getAllSpecKeys();
    public abstract boolean validateSerialNumber(int serialNumber);
    
}
