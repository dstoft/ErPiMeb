/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.domain.usermanager;

/**
 *
 * @author AKT
 */
public class Administrator {
    
    private int clearanceLevel;
    private String username;
    
    public Administrator(String username, int clearanceLevel){
        this.clearanceLevel = clearanceLevel;
        this.username = username;
    }
    
    
}
