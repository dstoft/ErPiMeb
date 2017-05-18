/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.gui.webshop;

import erpimeb.domain.usermanager.UserManager;
import erpimeb.domain.usermanager.UserManagerFacade;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author chris
 */
public class AdminLoginController implements Initializable {
    private Stage stageRef;
    private Scene preSceneRef;
    private String preSceneTitle;
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/PimMain.fxml"));
            Parent root = null;
            Scene scene;
            try {
                root = loader.load();
            } catch (IOException ex) {
                Logger.getLogger(AdminLoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            erpimeb.gui.pim.MainController pimMainController = (erpimeb.gui.pim.MainController) loader.getController();
            pimMainController.setReferences(this.stageRef,this.login.getScene());
            scene = new Scene(root);
            this.stageRef.setScene(scene);
            this.stageRef.setTitle("PIM Backend");
            this.stageRef.show();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Log ind kunne ikke gennemføres");
            alert.setHeaderText("Der er en fejl i enten brugernavn og password kombinationen.\nEller denne bruger har ikke de nødvendige rettigheder.");
            alert.showAndWait();
        }
        
        
    }

    void setReferences(Stage stageRef,Scene preSceneRef,String preSceneTitle) {
        this.stageRef = stageRef;
        this.preSceneRef = preSceneRef;
        this.preSceneTitle = preSceneTitle;
    }

    @FXML
    private void handleReturnToParent(ActionEvent event) {
        this.stageRef.setScene(this.preSceneRef);
        this.stageRef.setTitle(this.preSceneTitle);
        this.stageRef.show();
    }
    
}
