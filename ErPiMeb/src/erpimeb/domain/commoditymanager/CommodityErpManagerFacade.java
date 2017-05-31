/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.domain.commoditymanager;

import java.util.List;

/**
 *
 * @author chris
 */
public interface CommodityErpManagerFacade {
    public abstract List<Integer> getSerialNumbers();
    public abstract void fillProduct(Product product);
    public abstract boolean validateSerialNumber(int serialNumber);
    
}
