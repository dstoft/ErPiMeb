/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserManager;

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
    
    
    public static UserManager getInstance(){
        if(manager == null){
            manager = new UserManager();
        }
        return manager;
    }
}
