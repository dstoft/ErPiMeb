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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


/**
 * FXML Controller class
 *
 * @author chris
 */
public class AdminLoginController implements Initializable, Switchable {
    private UserManagerFacade userManager;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button login;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.userManager = UserManager.getInstance();
    }    

    @FXML
    private void handleLoginAction(ActionEvent event) {
        if(this.userManager.adminLogin(this.username.getText(), this.password.getText())){
            SceneSwitcher.changeScene("/resources/PimMain.fxml", "PIM Backend");
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Log ind kunne ikke gennemføres");
            alert.setHeaderText("Der er en fejl i enten brugernavn og password kombinationen.\nEller denne bruger har ikke de nødvendige rettigheder.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleReturnToParent(ActionEvent event) {
        SceneSwitcher.cycleBackward();
    }

    @Override
    public void setupInternals() {
        //
        if(this.userManager.getCurrentAdmin() != null){
            SceneSwitcher.changeScene("/resources/PimMain.fxml", "PIM Backend");
        }
    }
    
}
