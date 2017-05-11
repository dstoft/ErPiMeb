/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MailManager;

/**
 * Out of scope
 * @author AKT
 */
public class MailManager implements Facade{
    public static MailManager manager;
    
    public static MailManager getInstance(){
        if(manager == null){
            manager = new MailManager();
        }
        return manager;
    }
}
