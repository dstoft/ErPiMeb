/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.domain.ordermanager;

import erpimeb.domain.commoditymanager.Product;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AKT
 */
public class ReturnCase {
    
    private String status;
    private Order order;

    public ReturnCase(Order order) {
        this.order = order;
    }

    public String getStatus() {
        return status;
    }

    public Order getOrder() {
        return order;
    }
}
