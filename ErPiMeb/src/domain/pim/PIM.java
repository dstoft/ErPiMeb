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

    public boolean adminLogin(String username, String password) {

        boolean loginStatus = checkCredentials(username, password);
        return loginStatus;
    }

    public boolean checkCredentials(String username, String password) {

        throw new UnsupportedOperationException("Method not yet supported.");
    }

    public void editDescription(String newText) {
        // TODO: call editDescription in class Product
    }

    public void pickAndAttachImage(Image newImage) {
        // TODO: call pickAndAttachImage in class Product
    }

    public boolean uploadImage(Image image) {
        throw new UnsupportedOperationException("Method not yet supported.");
    }
    
    public void addTag(String tag) {
        // TODO: call addTag in class Product
    }
    
    public void addVideoLink(String videoLink) {
        // TODO: call addVideoLink in class Product
    }
    
    public void addSpecification(String name, String description) {
        // TODO: call addSpecification in class Product
    }
    
    public void addRelatedProduct(int productId) {
        // TODO: call addRelatedProduct in class Product
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
}
