/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.webshop;

import java.util.List;

/**
 *
 * @author AKT
 */
public class Checkout {
    
    private boolean termsOfServiceVerification;
    private String paymentMethod;
    private List<Integer> checkoutContents;
    private double checkoutPrice;
    private Order newOrder;
    
}
