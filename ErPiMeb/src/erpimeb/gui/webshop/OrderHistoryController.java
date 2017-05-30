/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.gui.webshop;

import erpimeb.domain.ordermanager.Order;
import erpimeb.domain.ordermanager.OrderManager;
import erpimeb.domain.ordermanager.OrderManagerFacade;
import erpimeb.domain.usermanager.UserManager;
import erpimeb.domain.usermanager.UserManagerFacade;
import erpimeb.gui.SceneSwitcher;
import erpimeb.gui.Switchable;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.TimeZone;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author chris
 */
public class OrderHistoryController implements Initializable, Switchable {
    private UserManagerFacade umf;
    private OrderManagerFacade omf;
    
    @FXML
    private GridPane orderGrid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.umf = UserManager.getInstance();
        this.omf = OrderManager.getInstance();
    }    

    @FXML
    private void handleReturnToParent(ActionEvent event) {
        SceneSwitcher.cycleBackward();
    }

    @Override
    public void setupInternals() {
        int i = 0;
        for(Order order : this.umf.getOrderHistory()){
            AnchorPane innerOrderPane = new AnchorPane();
            innerOrderPane.setPrefSize(426, 70);
            innerOrderPane.setLayoutX(15);
            innerOrderPane.setLayoutY(10);
            
            Label innerProductLabelId = new Label(String.valueOf(order.getOrderNumber()));
            innerProductLabelId.setLayoutX(65);
            innerProductLabelId.setLayoutY(20);
            
            Label innerProductLabelPrice = new Label(String.valueOf(order.getTotal()));
            innerProductLabelId.setLayoutX(150);
            innerProductLabelId.setLayoutY(20);
            
            Date date = new Date((long)order.getTimeStamp());
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT+2"));
            String formattedDate = sdf.format(date);
            
            Label innerProductLabelTimestamp = new Label(formattedDate);
            innerProductLabelTimestamp.setLayoutX(250);
            innerProductLabelTimestamp.setLayoutY(25);
            
            Button innerProductButton = new Button("Se ordre");
            innerProductButton.setLayoutX(350);
            innerProductButton.setLayoutY(20);
            innerProductButton.setOnAction(new EventHandler() {
                @Override
                public void handle(Event event) {
                    omf.setCurrentOrder(order);
                    SceneSwitcher.changeScene("/resources/WebshopViewOrder.fxml", "Ordre: " + String.valueOf(order.getOrderNumber()));
                }
            });
            
            innerOrderPane.getChildren().add(innerProductLabelId);
            innerOrderPane.getChildren().add(innerProductLabelPrice);
            innerOrderPane.getChildren().add(innerProductLabelTimestamp);
            innerOrderPane.getChildren().add(innerProductButton);
            
            this.orderGrid.addRow(i,innerOrderPane);
            i++;
        }
    }
    
}
