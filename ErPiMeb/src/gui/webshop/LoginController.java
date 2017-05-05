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
    private Scene preSceneRef;
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
        // TODO
    }    

    @FXML
    private void handleAdminLogin(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/WebshopAdminLogin.fxml"));
        Parent root = null;
        Scene scene;
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        AdminLoginController adminLoginController = (AdminLoginController) loader.getController();
        adminLoginController.setReferences(this.stageRef,this.adminLogin.getScene(),this.stageRef.getTitle());
        scene = new Scene(root);
        this.stageRef.setScene(scene);
        this.stageRef.setTitle("Administrator Login");
        this.stageRef.show();
    }

    void setReferences(Stage primaryStage,Scene preSceneRef) {
        this.stageRef = primaryStage;
        this.preSceneRef = preSceneRef;
    }

    @FXML
    private void handleLoginAction(ActionEvent event) {
    }

    @FXML
    private void handleCreateUser(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/WebshopCreateUser.fxml"));
        Parent root = null;
        Scene scene;
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        CreateUserController createUserController = (CreateUserController) loader.getController();
        createUserController.setReferences(this.stageRef,this.adminLogin.getScene(),this.stageRef.getTitle());
        scene = new Scene(root);
        this.stageRef.setScene(scene);
        this.stageRef.setTitle("Opret Bruger");
        this.stageRef.show();
    }

    @FXML
    private void handleReturnToParent(ActionEvent event) {
        this.stageRef.setScene(this.preSceneRef);
        this.stageRef.setTitle("Webshop");
        this.stageRef.show();
    }
    
}
