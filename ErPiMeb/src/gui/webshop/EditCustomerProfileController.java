/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.webshop;

import UserManager.Address;
import UserManager.Customer;
import UserManager.UserManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AKT
 */
public class EditCustomerProfileController implements Initializable {

    private Stage stageRef;
    private Scene preSceneRef;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField rePasswordField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField zipField;
    @FXML
    private TextField countryField;
    @FXML
    private Label statusLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UserManager currentUser = UserManager.getInstance();

        try {
            Customer currentCustomer = currentUser.getCurrentCustomer();
            emailField.setText(currentCustomer.getEmail());
            phoneField.setText(currentCustomer.getPhoneNumber());
            addressField.setText(currentCustomer.getAddress().getAddress());
            zipField.setText(Integer.toString(currentCustomer.getAddress().getZip()));
            countryField.setText(currentCustomer.getAddress().getCountry());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void setReferences(Stage primaryStage, Scene preSceneRef) {
        this.stageRef = primaryStage;
        this.preSceneRef = preSceneRef;
    }

    @FXML
    private void handleReturnToParent(ActionEvent event) {
        this.stageRef.setScene(this.preSceneRef);
        this.stageRef.setTitle("Webshop");
        this.stageRef.show();
    }

    @FXML
    private void handleSaveChanges(ActionEvent event) {
        try {
            if (passwordField.getText().equals(rePasswordField.getText()) && checkFieldsFiilled() == true) {
                statusLabel.setText("");
                UserManager currentUser = UserManager.getInstance();
                Address newAddress = new Address(addressField.getText(), Integer.parseInt(zipField.getText()), countryField.getText());

                currentUser.saveCustomerChanges(firstNameField.getText() + " " + lastNameField.getText(),
                        emailField.getText(), passwordField.getText(), phoneField.getText(), newAddress);
            } else if (checkFieldsFiilled() == false) {
                statusLabel.setText("Required fields have not been filled. Try again.");
            } else {
                statusLabel.setText("Passwords must match.");
            }
        } catch (Exception e) {
            statusLabel.setText("Unexpected error. Try again.");
        }
    }

    public boolean checkFieldsFiilled() {
        // If fields are unaltered, change nothing.
        if (emailField.getText().isEmpty() || firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty()) {
            return false;
        }

        return true;
    }

}
