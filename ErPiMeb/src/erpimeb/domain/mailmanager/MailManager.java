/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.domain.mailmanager;

import erpimeb.domain.ordermanager.Order;

/**
 * Out of scope
 * @author AKT
 */
public class MailManager implements MailManagerFacade{
    public static MailManager manager;
    
    public static MailManager getInstance(){
        if(manager == null){
            manager = new MailManager();
        }
        return manager;
    }

    @Override
    public boolean validateEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void emailReceipt(Order order) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
