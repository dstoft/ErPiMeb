/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.webshop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Agger
 */
class ProductCategoryWrapper {
    private String name;
    private List<String> subCategories;
    private List<Integer> productIds;
    
    public ProductCategoryWrapper(String name, Integer... productIds){
        this.name = name;
        this.productIds = new ArrayList<>();
        this.productIds.addAll(Arrays.asList(productIds));
        this.subCategories = new ArrayList<>();
    }
    
    public void addSubCategories(String... subCategoryNames){
        this.subCategories.addAll(Arrays.asList(subCategoryNames));
    }
    
    public String getName(){
        return this.name;
    }
    
    public List<String> getSubcategories(){
        return this.subCategories;
    }
    
    public List<Integer> getProductIds(){
        return this.productIds;
    }
    
    @Override
    public String toString(){
        return this.name;
    }
}
