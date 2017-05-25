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
    public static CommodityManager manager;
    
    public static CommodityManager getInstance(){
        if(manager == null){
            manager = new CommodityManager();
        }
        return manager;
    }
    
    private Set<Category> productCategories;
    private ArrayList<Product> products;
    private Product currentProduct;
    
    private DatabaseManagerFacade dbManager = DatabaseManager.getInstance();

    @Override
    public void fillProduct(Product product) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean createProduct(String name, List<String> images, List<String> videoLinks, String description, HashMap<String, String> specifications, double price, List<Product> relatedProducts) {
        Product newProduct = new Product(name, images, videoLinks, description, specifications, price, relatedProducts);
        
        newProduct.setRelatedProducts(this.dbManager.getRelatedProducts(specifications));
        newProduct.setCategory(new Category());
        
        return dbManager.saveProduct(newProduct);
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
    public List<Product> searchForProduct(String productName) {
        //return dbManager.searchForProduct(productName);
        
        // Test data:
        Product hej = new Product(1);
        hej.setName("hej produktet");
        hej.setDescription("Dette er en hej.");
        List<Product> hejlisten = new ArrayList<>();
        hejlisten.add(hej);
        return hejlisten;
    }

    @Override
    public void pickProductToEditFromList(Product pickedProduct) {
        currentProduct = pickedProduct;
    }
    
    @Override
    public boolean saveChangesToProduct() {
        return dbManager.saveProduct(currentProduct);
    }

    @Override
    public List<String> getAllSpecKeys() {
        return this.dbManager.getAllSpecKeys();
    }
    
    
}
