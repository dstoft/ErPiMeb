/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.webshop;

/**
 *
 * @author AKT
 */
public class ReturnCase {
    
    private int returnId;
    private String returnKind;
    private int orderNumber;
    
    public ReturnCase(int orderNumber, String returnKind) {
        this.orderNumber = orderNumber;
        this.returnKind = returnKind;
        // returnId is made in the database so that it is unique and increments.
    }
}
