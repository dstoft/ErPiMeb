/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.domain.commoditymanager;

import erpimeb.persistence.databasemanager.DatabaseManager;
import erpimeb.persistence.databasemanager.DatabaseManagerFacade;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 *
 * @author chris
 */
public class CommodityManager implements CommodityManagerFacade {
    private Category currentCategory;
    private Product currentProduct;
    
    public static CommodityManager manager;
    private DatabaseManagerFacade dbManager;
    
    public static CommodityManager getInstance(){
        if(manager == null){
            manager = new CommodityManager();
            manager.init();
        }
        return manager;
    }
    
    public void init(){
        this.dbManager = DatabaseManager.getInstance();
    }
    
    private Set<Category> productCategories;
    private ArrayList<Product> products;
    private String currentSearchTerm;
    
    @Override
    public void setSearchTerm(String searchTerm){
        this.currentSearchTerm = searchTerm;
    }
    
    @Override
    public String getSearchTerm(){
        return this.currentSearchTerm;
    }

    @Override
    public void fillProduct(Product product) {
        this.dbManager.fillProduct(product);
    }

    @Override
    public void createProduct(String name, List<String> images, List<String> videoLinks, String description, HashMap<String, String> specifications, double price, List<Product> relatedProducts) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void createCategory(String name, List<Category> subcategories, List<String> tagList, List<Product> products){
	Category newCategory = new Category(name, subcategories, tagList, products);
	
    }

    @Override
    public List<Category> showMainCategories() {
        return dbManager.getCategories();
    }

    @Override
    public void pickMainCategory(Category pickedMainCategory) {
        currentCategory = pickedMainCategory;
    }

    @Override
    public List<Product> searchForProduct(String productName) {
        return this.dbManager.searchForProduct(productName);
    }

    @Override
    public void pickProductToEditFromList(int productId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void pickProductFromList(Product product) {
        this.currentProduct = product;
    }

    @Override
    public Product getProduct() {
        return this.currentProduct;
    }

    @Override
    public void setCategory(Category category) {
        this.currentCategory = category;
    }

    @Override
    public Category getCategory() {
        return this.currentCategory;
    }
    
    public Category getCurrentCategory() {
        return currentCategory;
    }

    @Override
    public List<Category> showSubCategories(Category mainCategory) {
        return dbManager.getSubcategories(currentCategory.getName());
    }

    @Override
    public void pickSubCategory(Category pickedSubCategory) {
        currentCategory = pickedSubCategory;
    }

    @Override
    public List<Product> showProducts() {
        return currentCategory.getProductList();
    }

    @Override
    public Product getCurrentProduct() {
        return currentProduct;
    }

    @Override
    public void setCurrentProduct(Product product) {
        currentProduct = product;
    }

    @Override
    public List<String> getCurrentProductImages() {
        return currentProduct.getImages();
    }

    @Override
    public List<String> getCurrentProductVideos() {
        return currentProduct.getVideoLinks();
    }

    @Override
    public List<Category> showCategories() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Category pickCategory(String categoryName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Product> getNewProducts() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
