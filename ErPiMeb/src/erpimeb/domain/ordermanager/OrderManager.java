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
    public Order getSpecificOrder(int orderId) {
        return this.dmf.createOrder(orderId);
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

    @Override
    public void setCurrentOrder(Order order) {
        this.currentOrder = order;
    }

    @Override
    public Order getCurrentOrder() {
        return this.currentOrder;
    }
    
    @Override
    public ReturnCase showReturnForm(int orderId){
        // checks if the currentUser is a customer, since email is unique
        if (this.umf.getCurrentCustomer().getEmail() != null) {
            ReturnCase returnCase = new ReturnCase(dmf.fillOrder(orderId)); 
        }
            
        //If the currentUser is not a customer. 
        ReturnCase returnCase =  new ReturnCase(dmf.fillOrder(orderId));
        return returnCase;
    }
    
    //Saves the returnForm. 
    @Override
    public void submitReturnForm(ReturnCase returnCase){
        dmf.submitReturnForm(returnCase);
    }
    
    @Override
    public boolean iswarrantyExpired(ReturnCase returnCase){
        //Seconds in a year
        long sec2Year = 31921072;
        // checks if the warrenty is expired.
        if ((System.currentTimeMillis() - sec2Year) >= returnCase.getOrder().getTimeStamp()){
            return false;
        }
        return true; 
    }
}
