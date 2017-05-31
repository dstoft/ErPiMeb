/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package external.persistence.erpManager;

import erpimeb.domain.commoditymanager.CommodityErpManagerFacade;
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
public class ErpManager implements CommodityErpManagerFacade{
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
        //ONLY FOR DEBUGGING WHILE THE DB CONTAINS SERIAL NUMBERS THAT ARE NOT ACTUALLY SERIAL NUMBERS
        if(!this.serialNumbers.contains(product.getErpSn())) {
            product.setPrice(133799);
            return;
        }
        
        product.setPrice(this.price.get(product.getErpSn()));
    }

    @Override
    public boolean validateSerialNumber(int serialNumber) {
        return this.serialNumbers.contains(serialNumber);
    }
}