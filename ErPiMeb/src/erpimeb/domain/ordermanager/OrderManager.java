/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.domain.ordermanager;

import erpimeb.domain.usermanager.UserManager;
import erpimeb.domain.usermanager.UserManagerFacade;
import erpimeb.persistence.databasemanager.DatabaseManager;
import erpimeb.persistence.databasemanager.DatabaseManagerFacade;

/**
 *
 * @author chris
 */
public class OrderManager implements OrderManagerFacade{
    public static OrderManager manager;
    public  DatabaseManagerFacade databaseManager = DatabaseManager.getInstance(); 
    public UserManagerFacade userManager =  UserManager.getInstance(); 
    
    public static OrderManager getInstance(){
        if(manager == null){
            manager = new OrderManager();
        }
        return manager;
    }
    
    

    @Override
    public void setOrderName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setOrderAddress(String address, int zip, String country) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setOrderEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setOrderTOS(boolean status) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean confirmOrder() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cancelOrder() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order showSpecificOrder(int orderId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public ReturnCase showReturnForm(int orderId){
        // checks if the currentUser is a customer, since email is unique
        if (userManager.getCurrentCustomer().getEmail() != null) {
            ReturnCase returnCase = new ReturnCase(databaseManager.fillOrder(orderId)); 
        }
            
        //If the currentUser is not a customer. 
        ReturnCase returnCase =  new ReturnCase(databaseManager.fillOrder(orderId));
        return returnCase;
    }
    
    //Saves the returnForm. 
    @Override
    public void submitReturnForm(ReturnCase returnCase){
        databaseManager.submitReturnForm(returnCase);
    }
    
    @Override
    public boolean iswarrantyExpired(ReturnCase returnCase){
        //Seconds in a year
        long sec2Year = 31921072;
        // checks if the warrenty is expired.
        if ((System.currentTimeMillis() - sec2Year) >= returnCase.getOrder().getTimeStamp()){
            return false;
        }
        return true; 
    }
}
