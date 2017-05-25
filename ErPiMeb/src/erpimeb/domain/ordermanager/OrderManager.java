/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.domain.ordermanager;

import erpimeb.domain.commoditymanager.Product;
import erpimeb.domain.usermanager.Address;
import erpimeb.domain.usermanager.Customer;
import erpimeb.domain.usermanager.UserManager;
import erpimeb.domain.usermanager.UserManagerFacade;
import erpimeb.persistence.databasemanager.DatabaseManager;
import erpimeb.persistence.databasemanager.DatabaseManagerFacade;
import external.domain.mailmanager.MailManager;
import external.domain.mailmanager.MailManagerFacade;
import external.domain.paymentmanager.PaymentManager;
import external.domain.paymentmanager.PaymentManagerFacade;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chris
 */
public class OrderManager implements OrderManagerFacade{
    public static OrderManager manager;
    private Order currentOrder;
    private List<Product> products = new ArrayList<>();
    private UserManagerFacade umf;
    private MailManagerFacade mmf;
    private PaymentManagerFacade pmf;
    private DatabaseManagerFacade dmf;
    
    public OrderManager(){
        this.umf = UserManager.getInstance();
        this.mmf = MailManager.getInstance();
        this.pmf = PaymentManager.getInstance();
        this.dmf = DatabaseManager.getInstance();
    }
    
    public static OrderManager getInstance(){
        if(manager == null){
            manager = new OrderManager();
        }
        return manager;
    }

    @Override
    public void setOrderName(String name) {
        this.currentOrder.setName(name);
    }

    @Override
    public void setOrderAddress(String address, int zip, String country) {
        this.currentOrder.setAddress(address, zip, country);
    }

    @Override
    public void setOrderEmail(String email) {
        this.currentOrder.setEmail(email);
    }

    @Override
    public void setOrderTOS(boolean status) {
        this.currentOrder.setTos(status);
    }

    @Override
    public boolean confirmOrder() {
        if(this.currentOrder.isRequiredInformationFilled()){
            if(this.mmf.validateEmail(this.currentOrder.getEmail())){
                if(this.currentOrder.isTosVerified()){
                    if(this.pmf.iniatePayment()){
                        this.mmf.emailReceipt(this.currentOrder);
                        this.currentOrder.setStatus("Completed");
                        if(this.dmf.saveOrder(this.currentOrder)){
                            this.currentOrder.setAddress(null);
                            this.currentOrder = null;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void cancelOrder() {
        this.dmf.saveOrder(this.currentOrder);
        this.currentOrder.setAddress(null);
        this.currentOrder = null;
    }

    @Override
    public Order showSpecificOrder(int orderId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order startCheckout() {
        this.currentOrder = new Order();
        this.products = umf.getCartProducts();
        this.currentOrder.addProducts(this.products);
        Customer customer = umf.getCurrentCustomer();
        if(customer != null){
            String name = customer.getName(), email = customer.getEmail(), phoneNumber = customer.getPhoneNumber();
            this.currentOrder.setName(name);
            this.currentOrder.setEmail(email);
            this.currentOrder.setPhoneNumber(phoneNumber);
            Address address = customer.getAddress();
            customer.setAddress(address);
            customer = null;
        }
        return this.currentOrder;
    }
}
