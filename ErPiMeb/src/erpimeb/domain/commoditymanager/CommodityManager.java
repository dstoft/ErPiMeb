/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.domain.commoditymanager;

import erpimeb.persistence.databasemanager.DatabaseManager;
import external.persistence.erpManager.ErpManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 *
 * @author chris
 */
public class CommodityManager implements CommodityManagerFacade {
    private static CommodityManager manager;
    
    public static CommodityManager getInstance(){
        if(manager == null){
            manager = new CommodityManager();
        }
        return manager;
    }
    
    private CommodityManager(){
        this.dbManager = DatabaseManager.getInstance();
        this.erpManager = ErpManager.getInstance();
    }
    
    private Set<Category> productCategories;
    private ArrayList<Product> products;
    private String currentSearchTerm;
    private Category currentCategory;
    private Product currentProduct;
    private CommodityDatabaseManagerFacade dbManager;
    private CommodityErpManagerFacade erpManager;
    
    @Override
    public void setSearchTerm(String searchTerm){
        this.currentSearchTerm = searchTerm;
    }
    
    @Override
    public String getSearchTerm(){
        return this.currentSearchTerm;
    }

    // ***** Product *****
    @Override
    public void fillProduct(Product product) {
        this.dbManager.fillProduct(product);
        this.erpManager.fillProduct(product);
    }

    @Override
    public boolean createProduct(String name, List<String> images, List<String> videoLinks, String description, HashMap<String, String> specifications, int erpSn, Category pickedCategory) {
        Product newProduct = new Product(name, images, videoLinks, description, specifications, erpSn, pickedCategory);
        
        newProduct.setRelatedProducts(this.dbManager.getRelatedProducts(specifications));
        
        return dbManager.saveProduct(newProduct);
    }
    
    @Override
    public void setCurrentProduct(Product product) {
        this.currentProduct = product;
        if(product != null) {
            this.erpManager.fillProduct(this.currentProduct);
        }
    }

    @Override
    public Product getCurrentProduct() {
        if(this.currentProduct == null) {
            return null;
        }
        this.erpManager.fillProduct(this.currentProduct);
        return this.currentProduct;
    }
    
    @Override
    public boolean saveChangesToProduct() {
        this.currentProduct.setRelatedProducts(this.dbManager.getRelatedProducts(this.currentProduct.getSpecification()));
        
        return dbManager.saveProduct(currentProduct);
    }

    @Override
    public List<Product> searchForProduct(String productName) {
        return this.dbManager.searchForProduct(productName);
    }
    // ***** Product END *****
    
    // ***** Category *****
    @Override
    public boolean createCategory(String name, List<Category> subcategories, List<String> tagList, List<Product> products){
	Category newCategory = new Category(name, subcategories, tagList, products);
	return this.dbManager.saveCategory(newCategory);
    }

    @Override
    public void setCurrentCategory(Category category) {
        this.currentCategory = category;
    }
    
    @Override
    public Category getCurrentCategory() {
        return this.currentCategory;
    }

    @Override
    public List<Category> getMainCategories() {
        return dbManager.getMainCategories();
    }

    @Override
    public List<Category> getSubCategories(Category mainCategory) {
        return dbManager.getSubcategories(currentCategory.getName());
    }
    
    @Override
    public List<Category> getAllCategories() {
        return this.dbManager.getAllCategories();
    }

    @Override
    public List<Category> getNonMainCategories() {
        return this.dbManager.getNonMainCategories();
    }
    // ***** Category END *****
    
    @Override
    public List<String> getAllSpecKeys() {
        return this.dbManager.getAllSpecKeys();
    }

    @Override
    public boolean validateSerialNumber(int serialNumber) {
        if(this.dbManager.isErpSnAssigned(serialNumber)) {
            return false;
        }
        return this.erpManager.validateSerialNumber(serialNumber);
    }
}
