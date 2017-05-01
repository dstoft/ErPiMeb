/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.pim;

import java.util.*;

/**
 *
 * @author AKT
 */
public class ProductCatalog {

    private Set<ProductCategory> productCategories;
    private List<Product> products;

    public ProductCatalog() {
        productCategories = new TreeSet();
        products = new ArrayList<>();
    }

    public List<Integer> searchForProduct(String productName) {
        List<Integer> productIds = new ArrayList<>();
        
        for (Product p : products) {
            if (p.getName().equals(productName)) {
                productIds.add(p.getID());
            }
        }
        
        return productIds;
    }
    
    public void pickProductToEditFromList(int productId) {
       // TODO: ??? Possibly reliant on GUI
    }

    public Set getExistingProductCategories() {
        return productCategories;
    }

    public void saveProductCategory(ProductCategory newProductCategory) {
        productCategories.add(newProductCategory);
    }
}
