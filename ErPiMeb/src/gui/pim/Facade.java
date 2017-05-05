/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.pim;

import java.awt.Image;
import java.util.List;

/**
 *
 * @author chris
 */
public interface Facade {
    public boolean adminLogin(String username, String password);
    public boolean checkCredentials(String username, String password);
    public List<Integer> searchForProduct(String productName);
    public void pickProductToEditFromList(int productId);
    public boolean uploadImage(Image image);
    public void saveChanges();
    //public Graph viewCustomerData();                              // Need to determine what to do with Graph datatype
    //public Graph setSpecificTimeInterval(long start, long end);   // Need to determine what to do with Graph datatype
    public List<String> getSubCategories(String categoryName);
    public List<Integer> getProductsFromCategory(String categoryName);
    public String getProductName(int ProductId);
    public double getProductPrice(int productId);
    public List<Image> getProductImages(int productId);
    public List<String> getTagList(String categoryName);
    public List<String> getExistingProductCategories();
    public List<Integer> findProducts(List<String> tagList);
    public List<Integer> findProducts(String sortingOption);
    public List<Integer> getNewProductsFromErp();
}
