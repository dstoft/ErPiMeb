/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.gui.webshop;

import erpimeb.gui.SceneSwitcher;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import erpimeb.gui.Switchable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * FXML Controller class
 *
 * @author chris
 */
public class CustomerProfileController implements Initializable, Switchable {
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public void setupInternals() {
        
    }

    @FXML
    private void handleReturnToParent(ActionEvent event) {
        SceneSwitcher.cycleBackward();
    }

    @FXML
    private void handleEditProfile(ActionEvent event) {
        SceneSwitcher.changeScene("/resources/WebshopEditCustomerProfile.fxml", "Ændre profil");
    }

    @FXML
    private void handleViewOrderHistory(ActionEvent event) {
        SceneSwitcher.changeScene("/resources/WebshopOrderHistory.fxml", "Ordrehistorik");
    }
}
