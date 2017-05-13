/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OrderManager;

/**
 *
 * @author chris
 */
public class OrderManager implements Facade{
    public static OrderManager manager;
    private Order currentOrder;
    
    public static OrderManager getInstance(){
        if(manager == null){
            manager = new OrderManager();
        }
        return manager;
    }
}
