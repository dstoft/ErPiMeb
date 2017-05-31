/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.domain.commoditymanager;

import java.util.List;
import java.util.Map;

/**
 *
 * @author chris
 */
public interface CommodityDatabaseManagerFacade {
    public abstract void fillProduct(Product product);
    public abstract List<Product> getRelatedProducts(Map<String, String> specifications);
    public abstract boolean saveProduct(Product product);
    public abstract List<Product> searchForProduct(String productName);
    public abstract boolean saveCategory(Category category);
    public abstract List<Category> getMainCategories();
    public abstract List<Category> getSubcategories(String categoryName);
    public abstract List<Category> getAllCategories();
    public abstract List<Category> getNonMainCategories();
    public abstract List<String> getAllSpecKeys();
    public abstract boolean isErpSnAssigned(int erpSn);
    
    
}
