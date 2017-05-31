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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.TimeZone;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author chris
 */
public class ViewOrderController implements Initializable, Switchable {
    private OrderManagerFacade omf;
    
    @FXML
    private Label statusLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private ListView<String> orderProducts;
    @FXML
    private Label timestampLabel;

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
        for(Product product : currentOrder.getProducts()){
            this.orderProducts.getItems().add(product.getName());
        }
        this.priceLabel.setText(String.valueOf(currentOrder.getTotal()));
        this.statusLabel.setText(currentOrder.getStatus());
        Date date = new Date((long)currentOrder.getTimeStamp());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+2"));
        String formattedDate = sdf.format(date);
        this.timestampLabel.setText(formattedDate);
    }

    @FXML
    private void handleReturnToParent(ActionEvent event) {
        SceneSwitcher.cycleBackward();
    }

    @FXML
    private void handleCreateReturnCase(ActionEvent event) {
        this.omf.createAndSaveReturnCase();
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("A return case based on this order has been created and stored.");
        alert.showAndWait();
//        SceneSwitcher.changeScene("/resources/WebshopOrderHistory.fxml", "Retur sag");
        SceneSwitcher.cycleBackward();
        SceneSwitcher.cycleBackward();
    }
    
}
