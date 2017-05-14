/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserManager;

import DatabaseManager.DatabaseManager;
import java.util.List;

/**
 *
 * @author chris
 */
public class UserManager implements Facade{
    public static UserManager manager;
    private int currentUserId;
    private Customer currentUser;
    private Administrator currentAdmin;
    private Cart cart;
    private List<Customer> customers;
    private List<Administrator> admins;
    
    private DatabaseManager dbManager = DatabaseManager.getInstance();
    
    public static UserManager getInstance(){
        if(manager == null){
            manager = new UserManager();
        }
        return manager;
    }
    
    public Customer getCurrentCustomer() {
        currentUser = dbManager.createCustomer(currentUserId);
        
        return currentUser;
    }
    
    public boolean saveCustomerChanges(String name, String email, String phoneNumber, Address address) {
        currentUser.setUserInfo(name, email, phoneNumber, address);
        return dbManager.saveCustomer(currentUser);
    }
}
