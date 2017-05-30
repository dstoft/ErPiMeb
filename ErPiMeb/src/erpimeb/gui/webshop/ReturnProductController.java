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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author MatiasMarek
 */
public class ReturnProductController implements Initializable, Switchable {

    @FXML
    private TextField orderIdTextField;
    
    private OrderManagerFacade orderManager;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.orderManager = OrderManager.getInstance();
    }

    @FXML
    private void handleReturnToParent(ActionEvent event) {
        SceneSwitcher.cycleBackward();
    }

    @FXML
    private void handleGetOrder(ActionEvent event) {
        Order order = null;
        try{
            order = this.orderManager.getSpecificOrder(new Integer(this.orderIdTextField.getText()));
        } catch (NumberFormatException e){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Der skete en fejl!");
            alert.setHeaderText("Det indtastede ordre ID må kun bestå af tal.");
            alert.showAndWait();
            return;
        }
        if(order == null){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Ingen ordre med det ID");
            alert.setHeaderText("Der findes ingen ordre i vores system med det indtastede ID.");
            alert.showAndWait();
            return;
        }
        this.orderManager.setCurrentOrder(order);
        SceneSwitcher.changeScene("/resources/WebshopViewOrder.fxml", "Se ordre");
    }

    @Override
    public void setupInternals() {
        
    }

}
