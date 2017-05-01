/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.webshop;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author chris
 */
public class SearchForProductController implements Initializable {
    private Stage stageRef;
    @FXML
    private ListView<ProductWrapper> foundProducts;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ProductWrapper pw = new ProductWrapper(10);
        ObservableList data = FXCollections.observableList(Arrays.asList(pw));
    }    

    void setStageRef(Stage primaryStage) {
        this.stageRef = primaryStage;
    }

    @FXML
    private void handleChooseProduct(MouseEvent event) {
    }
    
}
