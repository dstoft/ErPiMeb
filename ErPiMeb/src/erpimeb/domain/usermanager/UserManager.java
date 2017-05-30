/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.domain.usermanager;

import erpimeb.domain.commoditymanager.CommodityManager;
import erpimeb.domain.commoditymanager.CommodityManagerFacade;
import erpimeb.domain.commoditymanager.Product;
import erpimeb.domain.ordermanager.Order;
import erpimeb.persistence.databasemanager.DatabaseManager;
import erpimeb.persistence.databasemanager.DatabaseManagerFacade;
import java.util.List;

/**
 *
 * @author chris
 */
public class UserManager implements UserManagerFacade{
    public static UserManager manager;
    
    public DatabaseManagerFacade dbManager;
    private CommodityManagerFacade cmf;
    
    private int currentUserId;
    private Customer currentUser;
    private Administrator currentAdmin;
    private Cart cart;
    private List<Customer> customers;
    private List<Administrator> admins;
    
    public UserManager() {
        this.dbManager = DatabaseManager.getInstance();
        this.cmf = CommodityManager.getInstance();
    }
    
    public static UserManager getInstance(){
        if(manager == null){
            manager = new UserManager();
        }
        return manager;
    }
    
    @Override
    public Customer getCurrentCustomer() {
        if(this.currentUserId > 0){
            currentUser = this.dbManager.fillCustomer(this.currentUserId);

            return this.currentUser;
        } else {
            return null;
        }
    }
    
    public boolean saveCustomerChanges(String name, String email, String password, String phoneNumber, Address address) {
        currentUser.setUserInfo(name, email, password, phoneNumber, address);
        return this.dbManager.saveCustomer(currentUser);
    }

    @Override
    public List<Product> getCartProducts() {
        List<Product> localProducts = this.cart.getProducts();
        for(Product prod : localProducts){
            this.cmf.fillProduct(prod);
        }
        return localProducts;
    }

    @Override
    public void saveCustomerChanges(String name, String email, String phoneNumber, String address, int zip, String country) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean adminLogin(String username, String password) {
        int clearance = this.dbManager.checkCredentials(username, password);
        if(clearance >= 2){
            this.currentAdmin = new Administrator(username, clearance);
            return true;
        }
        return false;
    }

    @Override
    public boolean createCustomer(String name, String password, String email, Address address, String phoneNumber) {
	String[] tempString;
	tempString = email.split("@");
	if(tempString.length == 1){
	    return false;
	}
	if(name.isEmpty() || password.isEmpty() || email.isEmpty() || address == null || phoneNumber.isEmpty()){
	    return false;
	} else if(!tempString[1].contains(".")){
	    return false;
	}
	Customer newCustomer = new Customer(name, address, phoneNumber, email, password);   
	dbManager.saveCustomer(newCustomer);
	return true;
    }

    @Override
    public boolean userLogin(String username, String password) {
        int userId = this.dbManager.checkCredentials(username, password);
        if(userId == 1){
            this.currentUserId = userId;
            return true;
        }
        return false;
    }

    @Override
    public List<Order> getOrderHistory() {
        return this.currentUser.getOrders();
    }

    @Override
    public Administrator getCurrentAdmin() {
        return this.currentAdmin;
    }
}
