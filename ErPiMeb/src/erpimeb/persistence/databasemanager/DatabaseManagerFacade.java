/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.persistence.databasemanager;

import erpimeb.domain.commoditymanager.Category;
import erpimeb.domain.commoditymanager.Product;
import erpimeb.domain.ordermanager.Order;
import erpimeb.domain.ordermanager.ReturnCase;
import erpimeb.domain.usermanager.Customer;
import java.util.List;
import java.util.Map;

/**
 *
 * @author chris
 */
public interface DatabaseManagerFacade {
    
    public abstract Customer fillCustomer(int userId);
    public abstract void fillProduct(Product product);
    public abstract Category fillCategory(String categoryName);
    public abstract void fillSubCategory(Category category);
    public abstract boolean saveOrder(Order order);
    public abstract boolean saveCustomer(Customer customer);
    public abstract boolean saveProduct(Product product);
    public abstract boolean saveCategory(Category category);
    public abstract Order createOrder(int orderId);
    public abstract int checkCredentials(String username, String password);
    public abstract List<Category> getMainCategories();
    public abstract List<Category> getSubcategories(String categoryName);
    public abstract List<Product> searchForProduct(String productName);
    public abstract List<Long> getOrderTimestamps(String status, long since);
    public abstract List<Integer> searchForProductId(String productName);
    public abstract List<Product> getRelatedProducts(Map<String, String> specifications);
    public abstract List<String> getAllSpecKeys();
    public abstract List<Category> getAllCategories();
    public abstract List<Category> getNonMainCategories();
    public abstract boolean isErpSnAssigned(int erpSn);
    public abstract boolean saveReturnCase(ReturnCase returnCase);
    public abstract Order fillOrder(int orderId); 
//    public abstract String getEmail(int orderId);
}
