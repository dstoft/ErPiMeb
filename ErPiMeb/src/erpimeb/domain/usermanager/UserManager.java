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
import java.util.List;

/**
 *
 * @author chris
 */
public class UserManager implements UserManagerFacade{
    private static UserManager manager;
    private UserDatabaseManagerFacade dbManager;
    private CommodityManagerFacade cmf;
    
    private int currentUserId;
    private Customer currentUser;
    private Administrator currentAdmin;
    private Cart cart;
    
    private UserManager() {
        this.dbManager = DatabaseManager.getInstance();
        this.cmf = CommodityManager.getInstance();
        this.cart = new Cart();
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
    
    @Override
    public void addProductToCart(Product product){
        this.cart.addProduct(product);
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
    public void saveCustomerChanges() {
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    @Override
    public boolean adminLogin(String username, String password) {
        int clearance = this.dbManager.checkCredentialsAdmin(username, password);
        if(clearance > 0){
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
    public boolean userLogin(String email, String password) {
        int userId = this.dbManager.checkCredentialsUser(email, password);
        if(userId != 0){
            this.currentUser = this.dbManager.fillCustomer(userId);
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
    
    @Override
    public void removeProductFromCart(Product product) {
        this.cart.removeProduct(product);
    }

    @Override
    public void removeOneProductFromCart(Product product) {
        this.cart.removeOneProduct(product);
    }

    @Override
    public double getTotalCartPrice() {
        return this.cart.getTotalPrice();
    }

    @Override
    public void clearCart() {
        this.cart = new Cart();
    }
}
