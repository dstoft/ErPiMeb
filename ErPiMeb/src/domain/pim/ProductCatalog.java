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
    
    public ProductCatalog() {
        productCategories = new TreeSet();
    }
    
    public Set getExistingProductCategories() {
        return productCategories;
    }
}
