/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.webshop;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author chris
 */
public class LoginController implements Initializable {
    private Stage stageRef;
    @FXML
    private Button adminLogin;
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
        // TODO
    }    

    @FXML
    private void handleAdminLogin(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        Scene scene;
        try {
            root = loader.load(getClass().getResource("AdminLogin.fxml").openStream());
        } catch (IOException ex) {
            Logger.getLogger(AdminLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        AdminLoginController adminLoginController = (AdminLoginController) loader.getController();
        adminLoginController.setStageRef(this.stageRef);
        scene = new Scene(root);
        this.stageRef.setScene(scene);
        this.stageRef.setTitle("Admin Login");
        this.stageRef.show();
    }

    void setStageRef(Stage primaryStage) {
        this.stageRef = primaryStage;
    }

    @FXML
    private void handleLoginAction(ActionEvent event) {
    }
    
}
