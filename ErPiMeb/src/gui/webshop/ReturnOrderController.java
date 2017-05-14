/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.webshop;

import domain.webshop.Facade;
import domain.webshop.WebshopController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AKT
 */
public class ReturnOrderController implements Initializable {

    private Stage stageRef;
    private Scene preSceneRef;
    private ObservableList<Integer> ids = FXCollections.observableArrayList();
    private Facade webshop = new WebshopController();
    
    @FXML
    private ListView<Integer> ordersListView;
    @FXML
    private ListView<String> orderProductsListView;
    @FXML
    private TextField totalTextField;
    @FXML
    private TextField dateTextField;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Add all elements of the users order history to list ids.
        for(int i : webshop.showOrderHistory()) {
            ids.add(i);
        }
        ordersListView = new ListView<>(ids);
        
    }    

    void setReferences(Stage primaryStage, Scene scene) {
        stageRef = primaryStage;
        preSceneRef = scene;
        
    }

    @FXML
    private void handleReturnToParent(ActionEvent event) {
        this.stageRef.setScene(this.preSceneRef);
        this.stageRef.setTitle("Webshop");
        this.stageRef.show();
    }

    @FXML
    private void handleCreateReturnCase(ActionEvent event) {
    }
    
}
