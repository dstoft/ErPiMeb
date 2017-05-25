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
    public abstract List<Category> showMainCategories();
    public abstract List<Category> showSubCategories(Category mainCategory);
    public abstract List<Product> showProducts();
    public abstract void pickMainCategory(Category pickedMainCategory);
    public abstract void pickSubCategory(Category pickedSubCategory);
    public abstract List<Product> searchForProduct(String productName);
    public abstract void pickProductToEditFromList(int productId);
    public abstract Category getCurrentCategory();
    public abstract Product getCurrentProduct();
    public abstract void setCurrentProduct(Product product);
    public abstract List<String> getCurrentProductImages();
    public abstract List<String> getCurrentProductVideos();
}
