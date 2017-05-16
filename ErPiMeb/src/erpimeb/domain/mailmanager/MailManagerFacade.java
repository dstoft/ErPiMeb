/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.domain.mailmanager;

import erpimeb.domain.ordermanager.Order;

/**
 * Out of scope
 * @author chris
 */
public interface MailManagerFacade {
    
    public abstract boolean validateEmail(String email);
    public abstract void emailReceipt(Order order);
    
}
