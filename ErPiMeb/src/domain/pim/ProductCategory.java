/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.pim;

import java.util.List;

/**
 *
 * @author AKT
 */
public class ProductCategory {
    
    private String name;
    private List<String> subCategories;
    private List<String> tagList;
    private List<Integer> productList;
    
    public ProductCategory(String name, List<String> subCategories, List<String> tagList) {
        this.name = name;
        this.subCategories = subCategories;
        this.tagList = tagList;
    }
}
