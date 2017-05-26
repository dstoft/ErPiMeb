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
    
    public abstract void fillProduct(Product product);
    public abstract boolean createProduct(String name, List<String> images, List<String> videoLinks, String description, HashMap<String, String> specifications, Category pickedCategory);
    public abstract List<Category> showCategories();
    public abstract Category pickCategory(String categoryName);
    public abstract List<Product> searchForProduct(String productName);
    public abstract void pickProductToEditFromList(Product pickedProduct);
    public abstract boolean saveChangesToProduct();
    public abstract List<String> getAllSpecKeys();
    public abstract List<Category> getAllCategories();
    public abstract Product getPickedProduct();
    
}
