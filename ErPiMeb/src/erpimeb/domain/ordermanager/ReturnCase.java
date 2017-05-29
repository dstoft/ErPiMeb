/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.domain.ordermanager;

/**
 *
 * @author AKT
 */
public class ReturnCase {
    
    private String status;
    private Order order;
    
    public ReturnCase(Order order){
        this.order = order;
    }
    
    
}
