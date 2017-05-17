/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.gui.webshop;

import erpimeb.domain.usermanager.Address;
import erpimeb.domain.usermanager.UserManager;
import erpimeb.domain.usermanager.UserManagerFacade;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author chris
 */
public class CreateUserController implements Initializable {
    private Stage stageRef;
    private Scene preSceneRef;
    private String preSceneTitle;
    public UserManagerFacade umFacade;
    @FXML
    private TextField emailField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField rePasswordField;
    @FXML
    private TextField phoneField;
    private TextField addressField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField zipTextField;
    @FXML
    private TextField countryTextField;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
	umFacade = UserManager.getInstance();
    }    

    void setReferences(Stage stageRef,Scene preSceneRef,String preSceneTitle) {
        this.stageRef = stageRef;
        this.preSceneRef = preSceneRef;
        this.preSceneTitle = preSceneTitle;
    }

    @FXML
    private void handleCreateUser(ActionEvent event) {
	String fullName = firstNameField.getText();
	fullName += " ";
	fullName += lastNameField.getText();
	if(umFacade.createCustomer(fullName, passwordField.getText(), emailField.getText(), new Address(streetField.getText(), Integer.parseInt(zipTextField.getText()), countryTextField.getText()), phoneField.getText())){ 
	    handleReturnToParent(event);
	} else{ try{
	    Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Oprettelse af bruger kunne ikke gennemføres");
            alert.setHeaderText("Der er en fejl i et af felterne, tjek dine informationer og prøv igen.");
	    alert.showAndWait();
	} catch(NumberFormatException nfe){ 	
	}
	}
    }

    @FXML
    private void handleReturnToParent(ActionEvent event) {
        this.stageRef.setScene(this.preSceneRef);
        this.stageRef.setTitle(this.preSceneTitle);
        this.stageRef.show();
    }
    
}
