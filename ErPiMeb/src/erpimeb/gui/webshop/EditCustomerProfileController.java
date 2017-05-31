/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.gui.webshop;

import erpimeb.domain.usermanager.Address;
import erpimeb.domain.usermanager.Customer;
import erpimeb.domain.usermanager.UserManager;
import erpimeb.domain.usermanager.UserManagerFacade;
import erpimeb.gui.SceneSwitcher;
import erpimeb.gui.Switchable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author AKT
 */
public class EditCustomerProfileController implements Initializable, Switchable {

    private UserManagerFacade userManager;

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
        this.userManager = UserManager.getInstance();

        try {
            Customer currentCustomer = this.userManager.getCurrentCustomer();
            emailField.setText(currentCustomer.getEmail());
            phoneField.setText(currentCustomer.getPhoneNumber());
            addressField.setText(currentCustomer.getAddress().getAddress());
            zipField.setText(Integer.toString(currentCustomer.getAddress().getZip()));
            countryField.setText(currentCustomer.getAddress().getCountry());
        } catch (NumberFormatException e) {
            statusLabel.setText("Der skete en fejl, ved hentning af postnummeret");
        }

    }

    @FXML
    private void handleReturnToParent(ActionEvent event) {
        SceneSwitcher.cycleBackward();
    }

    @FXML
    private void handleSaveChanges(ActionEvent event) {
        try {
            if (passwordField.getText().equals(rePasswordField.getText()) && checkFieldsFilled()) {
                Customer currentCustomer = this.userManager.getCurrentCustomer();
                currentCustomer.setName(firstNameField.getText() + " " + lastNameField.getText());
                currentCustomer.setPassword(this.passwordField.getText());
                currentCustomer.setEmail(this.emailField.getText());
                currentCustomer.setPhoneNumber(this.phoneField.getText());
                currentCustomer.setAddress(new Address(this.addressField.getText(), Integer.parseInt(zipField.getText()), this.countryField.getText()));
                this.userManager.saveCustomerChanges();
                SceneSwitcher.cycleBackward();
            } else if (!checkFieldsFilled()) {
                statusLabel.setText("De nødvendige felter må ikke være tomme. Prøv igen.");
            } else {
                statusLabel.setText("De indtastede kodeord stemmer ikke overens.");
            }
        } catch (NumberFormatException e) {
            statusLabel.setText("Dit postnummer må kun bestå af tal. Prøv igen.");
        }
    }

    private boolean checkFieldsFilled() {
        // If fields are unaltered, change nothing.
        if (emailField.getText().isEmpty() || firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty()) {
            return false;
        }

        return true;
    }

    @Override
    public void setupInternals() {

    }

}
