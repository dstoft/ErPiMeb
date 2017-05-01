/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.pim;

import java.awt.Image;
import java.util.*;

/**
 *
 * @author AKT
 */
public class PIM {
    
    private Product currentProduct;
    
    public PIM () {
        // Set current product to be equal to the product currently worked on
        currentProduct = new Product();
    }

    public boolean adminLogin(String username, String password) {

        boolean loginStatus = checkCredentials(username, password);
        return loginStatus;
    }

    public boolean checkCredentials(String username, String password) {
        // TODO: Check credentials with the database
        throw new UnsupportedOperationException("Method not yet supported.");
    }
    
    public List<Integer> searchForProduct(String productName) {
        List<Integer> products = new ArrayList<>();
        // TODO: call searchForProduct in class ProductCatalog
        // and fill products with that input.
        return products;
    }

    public void editDescription(String newText) {
        currentProduct.editDescription(newText);
    }
    
    public void pickProductToEditFromList(int productId) {
       // TODO: Call pickProductToEditFromList on class ProductCatalog
    }

    public void pickAndAttachImage(Image newImage) {
        currentProduct.pickAndAttachImage(newImage);
    }

    public boolean uploadImage(Image image) {
        throw new UnsupportedOperationException("Method not yet supported.");
    }
    
    public void addTag(String tag) {
        currentProduct.addTag(tag);
    }
    
    public void addVideoLink(String videoLink) {
        currentProduct.addVideoLink(videoLink);
    }
    
    public void addSpecification(String name, String description) {
        currentProduct.addSpecification(name, description);
    }
    
    public void addRelatedProduct(int productId) {
        currentProduct.addRelatedProduct(productId);
    }
    
    public Set getExistingProductCategories() {
        Set<ProductCategory> categories = new TreeSet<>();
        // TODO: call getExistingProductCategories in class ProductCatalog
        // and fill categories with that input.
        return categories;
    }
    
    public void createProductCategory(String name, List<String> subCategories, List<String> tagList) {
        // TODO: Create a productCategory
    }
    
    public void chooseProductCategory(String name) {
        // TODO: Choose product category
    }
    
    public void saveChanges() {
        // TODO: Save changes
    }
}
