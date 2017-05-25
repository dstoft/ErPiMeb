/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.persistence.databasemanager;

import erpimeb.domain.commoditymanager.Category;
import erpimeb.domain.commoditymanager.Product;
import erpimeb.domain.ordermanager.Order;
import erpimeb.domain.usermanager.Customer;
import java.util.List;

/**
 *
 * @author chris
 */
public interface DatabaseManagerFacade {
    
    public abstract Customer fillCustomer(int userId);
    public abstract void fillProduct(Product product);
    public abstract Category fillCategory(String categoryName);
    
    public abstract boolean saveOrder(Order order);
    public abstract boolean saveCustomer(Customer customer);
    public abstract boolean saveProduct(Product product);
    public abstract boolean saveCategory(Category category);
    public abstract Order createOrder(int orderId);
    public abstract int checkCredentials(String username, String password);
    public abstract List<Category> getCategories();
    public abstract List<Category> getSubcategories(String categoryName);
    public abstract List<Product> searchForProduct(String productName);
    
    
    
}
