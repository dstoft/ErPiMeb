/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SaveForLater;

import java.awt.Image;
import java.util.List;

/**
 *
 * @author chris
 */
public interface WebshopFacadeDeprecated {
    public void contactCustomerSupport();
    public List<Integer> showOrderHistory();
    public void showSpecificOrder(int orderNumber);             // Might be removed at later time
    public void showReturnForm(int orderNumber);                // Might be removed at later time
    public void returnOrder(int orderNumber, String returnKind);
    public void showEditableOptions();                          // Might be removed at later time
    public boolean saveEditedProfile();
    public boolean loginUser(String username, String password);
    public boolean registerUser(String email, String password, String name, String address, int zip, String country, String phoneNumber);
    public List<String> chooseCategory(String categoryName);
    public void addTag(String tagName);
    public void removeTag(String tagName);
    public List<Image> sortAfter(String sortingOption);
    public boolean confirmAndInitiatePayment();
    public void cancelCheckout();
    public void setOrderName(String name);
    public void setOrderAddress(String address);
    public void setOrderZip(int zip);
    public void setOrderCountry(String country);
    
}
