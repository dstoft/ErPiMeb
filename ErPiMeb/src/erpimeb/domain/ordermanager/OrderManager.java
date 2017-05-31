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
    private OrderDatabaseManagerFacade dmf;
    
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
    public void setOrderPhone(String phoneNumber){
        this.currentOrder.setPhoneNumber(phoneNumber);
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
                    this.currentOrder.setPaymentMethod(this.pmf.iniatePayment());
                    this.mmf.emailReceipt(this.currentOrder);
                    this.currentOrder.setStatus("complete");
                    if(this.dmf.saveOrder(this.currentOrder, this.umf.getCurrentCustomer())){
                        this.umf.clearCart();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void cancelOrder() {
        if(this.currentOrder.isRequiredInformationFilled()) {
            this.dmf.saveOrder(this.currentOrder, this.umf.getCurrentCustomer());
            this.currentOrder.setAddress(null);
            this.currentOrder = null;
        }
        
    }

    @Override
    public Order getSpecificOrder(int orderId) {
        return this.dmf.fillOrder(orderId);
    }

    @Override
    public Order startCheckout() {
        this.currentOrder = new Order();
        this.currentOrder.addProducts(umf.getCartProducts());
        Customer customer = umf.getCurrentCustomer();
        if(customer != null){
            String name = customer.getName(), email = customer.getEmail(), phoneNumber = customer.getPhoneNumber();
            this.currentOrder.setName(name);
            this.currentOrder.setEmail(email);
            this.currentOrder.setPhoneNumber(phoneNumber);
            Address address = customer.getAddress();
            this.currentOrder.setAddress(address);
            customer = null;
        } else {
            this.currentOrder.setName(null);
            this.currentOrder.setEmail(null);
            this.currentOrder.setPhoneNumber(null);
            this.currentOrder.setAddress(null);
        }
        return this.currentOrder;
    }

    @Override
    public void setCurrentOrder(Order order) {
        this.currentOrder = order;
    }

    @Override
    public Order getCurrentOrder() {
        return this.currentOrder;
    }
    
    //Saves the returnForm. 
    @Override
    public void createAndSaveReturnCase(){
        ReturnCase returnCase = new ReturnCase(this.currentOrder);
        dmf.saveReturnCase(returnCase);
    }
    
    @Override
    public boolean iswarrantyExpired(ReturnCase returnCase){
        //Milliseconds on 2 years
        long sec2Year = 63072000000L;
        // checks if the warrenty is expired.
        if ((System.currentTimeMillis() - sec2Year) >= returnCase.getOrder().getTimeStamp()){
            return false;
        }
        return true; 
    }
}
