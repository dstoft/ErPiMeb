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
    public abstract void createProduct(String name, List<String> images, List<String> videoLinks, String description, HashMap<String, String> specifications, double price, List<Product> relatedProducts);
    public abstract void createCategory(String name, List<Category> subcategories, List<String> tagList, List<Product> productList);
    public abstract List<Category> showCategories();
    public abstract Category pickCategory(String categoryName);
    public abstract List<Product> searchForProduct(String productName);
    public abstract void pickProductToEditFromList(int productId);
    
}
