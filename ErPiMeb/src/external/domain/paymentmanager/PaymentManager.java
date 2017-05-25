/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package external.domain.paymentmanager;

/**
 * Out of scope
 * @author chris
 */
public class PaymentManager implements PaymentManagerFacade{
    public static PaymentManager manager;
    
    public static PaymentManager getInstance(){
        if(manager == null){
            manager = new PaymentManager();
        }
        return manager;
    }

    @Override
    public boolean iniatePayment() {
        return true;
    }
}
