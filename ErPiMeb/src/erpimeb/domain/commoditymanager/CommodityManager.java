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
public class CommodityManager implements CommodityManagerFacade{
    
    private Product product;
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

    @Override
    public void fillProduct(Product product) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public List<Category> showCategories() {
        return dbManager.getCategories();
    }

    @Override
    public Category pickCategory(String categoryName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Product> searchForProduct(String productName) {
        return this.dbManager.searchForProduct(productName);
    }

    @Override
    public void pickProductToEditFromList(int productId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
