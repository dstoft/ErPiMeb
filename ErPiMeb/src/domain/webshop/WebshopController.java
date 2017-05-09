/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.webshop;

import domain.mailserver.MailServer;
import java.util.*;

/**
 *
 * @author AKT
 */
public class WebshopController {
    
    private Customer currentCustomer;
    
    public void contactCustomerSupport(String emailAddress) {
        MailServer mailServer = new MailServer();
        
        if (mailServer.emailContactReceipt(emailAddress) == true) {
            // Let the user know, through GUI, that the receipt was sent.
        } else {
            // Let the user know that en error occured.
        }
    }
    
    public List<Integer> showOrderHistory() {
        List<Integer> orderHistory = new ArrayList<>();
        
        orderHistory = currentCustomer.getOrderHistoryAsList();
        
        return orderHistory;
    }
    
    public void showSpecificOrder(int orderNumber) {
        // GUI method (?)
    }
    
    public void showReturnForm(int orderNumber) {
        // GUI method (?)
    }
    
    public void returnOrder(int orderNumber, String returnKind) {
        // GUI method (?)
    }
}
