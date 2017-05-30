/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.gui.webshop;

import erpimeb.domain.ordermanager.OrderManager;
import erpimeb.domain.ordermanager.OrderManagerFacade;
import erpimeb.domain.ordermanager.ReturnCase;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author MatiasMarek
 */
public class WebshopReturnProductController implements Initializable {

    @FXML
    private Button returntoId;
    @FXML
    private TextField orderIdTextField;
    @FXML
    private Button orderIdPressed;
    
    private OrderManagerFacade orderManager;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.orderManager = OrderManager.getInstance();
        int enteredOrderId = Integer.parseInt(orderIdTextField.getText());
        ReturnCase filledOrder = orderManager.showReturnForm(enteredOrderId);
    }

    @FXML
    private void handleReturnToParent(ActionEvent event) {
        //SceneSwitcher.cycleBackward();

    }

    @FXML
    private void orderIdButton(ActionEvent event) {
        // Agger sceneSwitcher 
        
    }

}
