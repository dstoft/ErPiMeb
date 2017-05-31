/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.domain.ordermanager;

import erpimeb.domain.usermanager.Customer;

/**
 *
 * @author chris
 */
public interface OrderDatabaseManagerFacade {
    public abstract boolean saveOrder(Order order, Customer customer);
    public abstract Order fillOrder(int orderId);
    public abstract boolean saveReturnCase(ReturnCase returnCase);
    
}
