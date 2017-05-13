/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommodityManager;

import java.util.ArrayList;
import java.util.Set;

/**
 *
 * @author chris
 */
public class CommodityManager implements Facade{
    public static CommodityManager manager;
    
    public static CommodityManager getInstance(){
        if(manager == null){
            manager = new CommodityManager();
        }
        return manager;
    }
    
    private Set<ProductCategory> productCategories;
    private ArrayList<Product> products;
}
