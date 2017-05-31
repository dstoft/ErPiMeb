/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.gui.webshop;

import erpimeb.domain.ordermanager.Order;
import erpimeb.domain.ordermanager.OrderManager;
import erpimeb.domain.ordermanager.OrderManagerFacade;
import erpimeb.gui.SceneSwitcher;
import erpimeb.gui.Switchable;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Agger
 */
public class CheckoutController implements Initializable, Switchable {
    private OrderManagerFacade omf;
    
    private Order currentOrder;
    @FXML
    private Label totalPrice;
    @FXML
    private TextField orderEmail;
    @FXML
    private TextField orderName;
    @FXML
    private TextField orderAddress;
    @FXML
    private TextField orderZip;
    @FXML
    private TextField orderCountry;
    @FXML
    private TextField orderPhone;
    @FXML
    private CheckBox orderTos;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.omf = OrderManager.getInstance();
    }    

    @Override
    public void setupInternals() {
        this.currentOrder = this.omf.startCheckout();
        DecimalFormat df = new DecimalFormat("#.##");
        this.totalPrice.setText(df.format(this.currentOrder.getTotal()));
        this.orderEmail.setText(this.currentOrder.getEmail());
        this.orderName.setText(this.currentOrder.getName());
        this.orderPhone.setText(this.currentOrder.getPhoneNumber());
        if(this.currentOrder.getAddress() != null){
            this.orderAddress.setText(this.currentOrder.getAddress().getAddress());
            this.orderZip.setText(String.valueOf(this.currentOrder.getAddress().getZip()));
            this.orderCountry.setText(this.currentOrder.getAddress().getCountry());
        }
        
        ChangeListener addressHandler = new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue){
                } else {
                    if(orderZip.getText().length() != 0){
                        try{
                            omf.setOrderAddress(orderAddress.getText(), new Integer(orderZip.getText()), orderCountry.getText());
                        } catch(NumberFormatException nfe){
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Post nummeret er ikke gyldigt!");
                            alert.setHeaderText("Der blev fundet en fejl i postnummeret i din leveringsinformation. Ret venligst denne og prøv igen.");
                            alert.showAndWait();
                        }
                    }
                }
            }
        };
        
        this.orderCountry.focusedProperty().addListener(addressHandler);
        this.orderAddress.focusedProperty().addListener(addressHandler);
        this.orderZip.focusedProperty().addListener(addressHandler);
    }

    @FXML
    private void handleReturnToParent(ActionEvent event) {
        SceneSwitcher.cycleBackward();
    }

    @FXML
    private void confirmOrder(ActionEvent event) {
        if(this.omf.confirmOrder()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Din ordre er gennemført!");
            alert.setHeaderText("Din ordre er placeret og vi har registreret den i vores system. Du har modtaget en kvittering på mail.");
            alert.showAndWait();
            SceneSwitcher.changeScene("/resources/WebshopReceipt.fxml", "Webshop");
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Din ordre blev ikke gennemført!");
            alert.setHeaderText("Der skete en fejl under placeringen af din ordre! Det kan skyldes følgende:\n\nDin email addresse er ikke gyldig.\nDit kort blev afvist ved betaling.\nDe informationer du har givet på denne side er forkerte.");
            alert.showAndWait();
        }
    }

    @FXML
    private void updateOrderEmail(KeyEvent event) {
        this.omf.setOrderEmail(this.orderEmail.getText());
    }

    @FXML
    private void updateOrderName(KeyEvent event) {
        this.omf.setOrderName(this.orderName.getText());
    }

    @FXML
    private void updateOrderPhoneNumber(KeyEvent event) {
        this.omf.setOrderPhone(this.orderPhone.getText());
    }

    @FXML
    private void cancelOrder(ActionEvent event) {
        this.omf.cancelOrder();
    }

    @FXML
    private void updateOrderTos(ActionEvent event) {
        this.omf.setOrderTOS(this.orderTos.isSelected());
    }
}
