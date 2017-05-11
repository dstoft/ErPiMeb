/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OrderManager;

import CommodityManager.Product;
import java.util.List;

/**
 *
 * @author AKT
 */
public class Order {
    
    private boolean tempOrder;
    private int orderNumber;
    private List<Product> products;
    private double total;
    private String paymentMethod;
    private String deliveryInformation;
    private boolean tos;
    
}
