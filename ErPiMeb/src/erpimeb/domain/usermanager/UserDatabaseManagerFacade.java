/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.domain.usermanager;

/**
 *
 * @author chris
 */
public interface UserDatabaseManagerFacade {
    public abstract Customer fillCustomer(int userId);
    public abstract boolean saveCustomer(Customer customer);
    public abstract int checkCredentialsAdmin(String username, String password);
    public abstract int checkCredentialsUser(String email, String password);
    
}
