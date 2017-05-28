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
    private TextField productTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField refundTextField;
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
    }

    @FXML
    private void orderIdButton(ActionEvent event) {
        int enteredOrderId = Integer.parseInt(orderIdTextField.getText());
        ReturnCase filledOrder = orderManager.showReturnForm(enteredOrderId);
        filledOrder.
    }

}
