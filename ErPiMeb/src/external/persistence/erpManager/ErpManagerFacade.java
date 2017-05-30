/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package external.persistence.erpManager;

import erpimeb.domain.commoditymanager.Product;
import java.util.List;

/**
 *
 * @author chris
 */
public interface ErpManagerFacade {
    public abstract List<Integer> getSerialNumbers();
    public abstract void fillProduct(Product product);
    public abstract boolean validateSerialNumber(int serialNumber);
}
