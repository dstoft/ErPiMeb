/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package external.domain.mailmanager;

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
        String[] emailArr = email.split("@");
        if(emailArr.length != 2){
            return false;
        }
        return emailArr[1].contains(".");
    }

    @Override
    public void emailReceipt(Order order) {
        System.out.println(order.getEmail() + " bought products for total price of: " + order.getTotalPrice());
    }
}
