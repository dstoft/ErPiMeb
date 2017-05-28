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
    public abstract void createCategory(String name, List<Category> subcategories, List<String> tagList, List<Product> productList);
    public abstract List<Category> showCategories();
    public abstract Category pickCategory(String categoryName);
    public abstract List<Product> searchForProduct(String productName);
    public abstract void pickProductToEditFromList(int productId);
    public abstract void setSearchTerm(String searchTerm);
    public abstract String getSearchTerm();
    public abstract void pickProductFromList(Product product);
    public abstract Product getProduct();
    public abstract void setCategory(Category category);
    public abstract Category getCategory();
    public abstract List<Category> showMainCategories();
    public abstract List<Category> showSubCategories(Category mainCategory);
    public abstract List<Product> showProducts();
    public abstract void pickMainCategory(Category pickedMainCategory);
    public abstract void pickSubCategory(Category pickedSubCategory);
    public abstract Category getCurrentCategory();
    public abstract Product getCurrentProduct();
    public abstract void setCurrentProduct(Product product);
    public abstract List<String> getCurrentProductImages();
    public abstract List<String> getCurrentProductVideos();
    public abstract boolean createProduct(String name, List<String> images, List<String> videoLinks, String description, HashMap<String, String> specifications, Category pickedCategory);
    public abstract void pickProductToEditFromList(Product pickedProduct);
    public abstract boolean saveChangesToProduct();
    public abstract List<String> getAllSpecKeys();
    public abstract List<Category> getAllCategories();
    public abstract Product getPickedProduct();
}
