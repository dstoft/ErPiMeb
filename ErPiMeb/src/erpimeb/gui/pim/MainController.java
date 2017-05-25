/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.gui.pim;

import erpimeb.gui.SceneSwitcher;
import erpimeb.gui.Switchable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author chris
 */
public class MainController implements Initializable, Switchable {
    @FXML
    private Label productAmount;
    @FXML
    private Label orderAmount;
    @FXML
    private Label customerAmount;
    @FXML
    private Button createCategotyButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleCreateProduct(ActionEvent event) {
        SceneSwitcher.changeScene("/resources/PimCreateProduct.fxml", "PIM Backend - Opret Produkt");
    }

    @FXML
    private void handleEditProduct(ActionEvent event) {
        SceneSwitcher.changeScene("/resources/PimEditProduct.fxml", "PIM Backend - Ændre Produkt");
    }

    @FXML
    private void handleLogOut(ActionEvent event) {
        SceneSwitcher.cycleBackward();
    }

    @Override
    public void setupInternals() {
        
    }
    
    @FXML
    private void handleCreateCategory(ActionEvent event) {
        SceneSwitcher.changeScene("/resources/PimCreateCategory.fxml", "PIM Backend - Opret Kategori");
    }

    @FXML
    private void handleShowStatistic(ActionEvent event) {
        SceneSwitcher.changeScene("/resources/PimShowStatistic.fxml", "PIM Backend - Show statistic");
    }
}
