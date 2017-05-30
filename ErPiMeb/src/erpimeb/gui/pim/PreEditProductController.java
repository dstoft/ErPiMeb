/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.gui.pim;

import erpimeb.domain.commoditymanager.CommodityManager;
import erpimeb.domain.commoditymanager.CommodityManagerFacade;
import erpimeb.domain.commoditymanager.Product;
import erpimeb.gui.SceneSwitcher;
import erpimeb.gui.Switchable;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AKT
 */
public class PreEditProductController implements Initializable, Switchable {

    private Stage stageRef;
    private Scene preSceneRef;

    private CommodityManagerFacade cManager = CommodityManager.getInstance();

    @FXML
    private TextField searchTextField;
    @FXML
    private ListView<Product> searchListView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleReturnToParent(ActionEvent event) {
        SceneSwitcher.cycleBackward();
    }

    @FXML
    private void handleSearch(ActionEvent event) {
        searchListView.getItems().clear();
        List<Product> productsFound = cManager.searchForProduct(searchTextField.getText());

        searchListView.getItems().addAll(productsFound);
    }

    @FXML
    private void handlePickProduct(ActionEvent event) {
        if (searchListView.getSelectionModel().getSelectedItem() != null) {
            cManager.setCurrentProduct(searchListView.getSelectionModel().getSelectedItem());
            this.searchListView.getItems().clear();
            
            SceneSwitcher.changeScene("/resources/PimEditProduct.fxml", "PIM Backend - Rediger Produkt");
        }
    }

    @Override
    public void setupInternals() {
    }

}
