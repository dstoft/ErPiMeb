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
    
    public boolean contactCustomerSupport(String emailAddress) {
        MailServer mailServer = new MailServer();
        
        return mailServer.emailContactReceipt(emailAddress);
    }
    
    public List<Integer> showOrderHistory() {
        List<Integer> orderHistory = new ArrayList<>();
        
        orderHistory = currentCustomer.getOrderHistoryAsList();
        
        return orderHistory;
    }
}
