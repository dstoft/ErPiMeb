/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package external.persistence.erpManager;

import erpimeb.domain.commoditymanager.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author chris
 */
public class ErpManager implements ErpManagerFacade{
    private static ErpManager manager;
    
    public static ErpManager getInstance(){
        if(manager == null){
            manager = new ErpManager();
        }
        return manager;
    }
    
    private ErpManager(){
        this.price = new HashMap<>();
        this.serialNumbers = new ArrayList<>();
        this.createProductInformations();
    }
    
    private List<Integer> serialNumbers;
    private Map<Integer, Double> price;
    
    private void createProductInformations(){
        for(int i = 0; i < 100; i++){
            this.serialNumbers.add(i);
            this.price.put(i, new Random().nextDouble() * (new Random().nextInt(5000) + 1));
        }
    }
    
    @Override
    public List<Integer> getSerialNumbers() {
        return this.serialNumbers;
    }

    @Override
    public void fillProduct(Product product) {
        product.setPrice(this.price.get(product.getId()));
    }
}