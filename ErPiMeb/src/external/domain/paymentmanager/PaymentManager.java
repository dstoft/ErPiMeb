/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package external.domain.paymentmanager;

import erpimeb.domain.ordermanager.OrderManager;
import erpimeb.domain.ordermanager.OrderManagerFacade;

/**
 * Out of scope
 * @author chris
 */
public class PaymentManager implements PaymentManagerFacade{
    public static PaymentManager manager;
    private OrderManagerFacade omf;
    
    public static PaymentManager getInstance(){
        if(manager == null){
            manager = new PaymentManager();
        }
        return manager;
    }
    
    private PaymentManager(){
        this.omf = OrderManager.getInstance();
    }

    @Override
    public String iniatePayment() {
        return "VISA";
    }
}
