/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.gui.webshop;

import erpimeb.domain.commoditymanager.Product;
import erpimeb.domain.ordermanager.Order;
import erpimeb.domain.ordermanager.OrderManager;
import erpimeb.domain.ordermanager.OrderManagerFacade;
import erpimeb.gui.SceneSwitcher;
import erpimeb.gui.Switchable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author chris
 */
public class ReceiptController implements Initializable, Switchable {
    private OrderManagerFacade omf;
    
    @FXML
    private Label orderAddressLabel;
    @FXML
    private Label orderPriceLabel;
    @FXML
    private Label orderNameLabel;
    @FXML
    private Label orderEmailLabel;
    @FXML
    private Label orderNumberLabel;
    @FXML
    private ListView<String> orderProducts;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.omf = OrderManager.getInstance();
    }    

    @Override
    public void setupInternals() {
        Order currentOrder = this.omf.getCurrentOrder();
        this.orderNumberLabel.setText("" + currentOrder.getOrderID());
        this.orderEmailLabel.setText(currentOrder.getEmail());
        this.orderNameLabel.setText(currentOrder.getName());
        this.orderAddressLabel.setText(currentOrder.getAddress().getAddress() + ", " + currentOrder.getAddress().getZip() + ", " + currentOrder.getAddress().getCountry());
        this.orderPriceLabel.setText("" + currentOrder.getTotal());
        for(Product product : currentOrder.getProducts()){
            this.orderProducts.getItems().add(product.getName());
        }
    }

    @FXML
    private void handleReturnToMainPage(ActionEvent event) {
        SceneSwitcher.changeScene("/resources/WebshopMain.fxml", "Webshop");
    }
    
    
    
}
