/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.gui.webshop;

import erpimeb.domain.usermanager.UserManager;
import erpimeb.domain.usermanager.UserManagerFacade;
import erpimeb.gui.SceneSwitcher;
import erpimeb.gui.Switchable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author chris
 */
public class LoginController implements Initializable, Switchable {
    private UserManagerFacade userManager;
    @FXML
    private Button adminLogin;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button login;
    @FXML
    private Button createUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.userManager = UserManager.getInstance();
    }    

    @FXML
    private void handleAdminLogin(ActionEvent event) {
        SceneSwitcher.changeScene("/resources/WebshopAdminLogin.fxml", "Administrator Login");
    }

    @FXML
    private void handleLoginAction(ActionEvent event) {
        if(this.userManager.userLogin(this.username.getText(), this.password.getText())){
            SceneSwitcher.changeScene("/resources/WebshopCustomerProfile.fxml", userManager.getCurrentCustomer().getName());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Log ind kunne ikke gennemføres");
            alert.setHeaderText("Der er en fejl i brugernavn og password kombinationen.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleCreateUser(ActionEvent event) {
        SceneSwitcher.changeScene("/resources/WebshopCreateUser.fxml", "Opret Bruger");
    }

    @FXML
    private void handleReturnToParent(ActionEvent event) {
        SceneSwitcher.cycleBackward();
    }

    @Override
    public void setupInternals() {
        
    }
    
}
