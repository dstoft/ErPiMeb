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
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author AKT
 */
public class SearchForProductController implements Initializable, Switchable {

    private CommodityManagerFacade cManager;

    @FXML
    private TextField searchTextField;
    @FXML
    private ListView<Product> searchListView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cManager = CommodityManager.getInstance();
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
            
            SceneSwitcher.cycleBackward();
        }
    }

    @Override
    public void setupInternals() {
    }

}
