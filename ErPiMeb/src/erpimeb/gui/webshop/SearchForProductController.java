/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.gui.webshop;

import erpimeb.domain.commoditymanager.CommodityManager;
import erpimeb.domain.commoditymanager.CommodityManagerFacade;
import erpimeb.domain.commoditymanager.Product;
import erpimeb.gui.Switchable;
import erpimeb.gui.SceneSwitcher;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author chris
 */
public class SearchForProductController implements Initializable, Switchable {
    private CommodityManagerFacade commodityManager;
    @FXML
    private ListView<Product> foundProducts;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.commodityManager = CommodityManager.getInstance();
        /*
        Query PIM for products matching this.searchTerm
        Create a productWrapper object for each, based on a query for PIM module and put them into this.foundProducts listview.
        */
        this.foundProducts.getItems().addAll(this.commodityManager.searchForProduct(commodityManager.getSearchTerm()));
        
    }

    @FXML
    private void handleChooseProduct(MouseEvent event) {
        if(this.foundProducts.getSelectionModel().getSelectedItem() != null){
            commodityManager.pickProductFromList(this.foundProducts.getSelectionModel().getSelectedItem());
            SceneSwitcher.changeScene("/resources/WebshopViewProduct.fxml", "vis produktets navn her");
        }
    }

    @FXML
    private void handleReturnToParent(ActionEvent event) {
        SceneSwitcher.cycleBackward();
    }

    @Override
    public void setupInternals() {
        
    }
}
