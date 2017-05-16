/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.gui.webshop;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author chris
 */
public class SearchForProductController implements Initializable {
    private Stage stageRef;
    private Scene preSceneRef;
    @FXML
    private ListView<ProductWrapper> foundProducts;
    private String searchTerm;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*
        Query PIM for products matching this.searchTerm
        Create a productWrapper object for each, based on a query for PIM module and put them into this.foundProducts listview.
        */
        
        ProductWrapper pw = new ProductWrapper(1);
        this.foundProducts.getItems().add(pw);
    }    

    void setReferences(Stage primaryStage, Scene preSceneRef) {
        this.stageRef = primaryStage;
        this.preSceneRef = preSceneRef;
    }

    @FXML
    private void handleChooseProduct(MouseEvent event) {
        if(this.foundProducts.getSelectionModel().getSelectedItem() != null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/WebshopViewProduct.fxml"));
            Parent root = null;
            Scene scene;
            try {
                root = loader.load();
            } catch (IOException ex) {
                Logger.getLogger(SearchForProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
            ViewProductController viewProductController = (ViewProductController) loader.getController();
            viewProductController.setReferences(this.stageRef,this.foundProducts.getScene(),this.stageRef.getTitle());
            viewProductController.setProduct(this.foundProducts.getSelectionModel().getSelectedItem());
            viewProductController.setupScene();
            scene = new Scene(root);
            this.stageRef.setScene(scene);
            this.stageRef.setTitle(this.foundProducts.getSelectionModel().getSelectedItem().getName());
            this.stageRef.show();
        }
    }

    void setSearchTerm(String term) {
        this.searchTerm = term;
    }

    @FXML
    private void handleReturnToParent(ActionEvent event) {
        this.stageRef.setScene(this.preSceneRef);
        this.stageRef.setTitle("Webshop");
        this.stageRef.show();
    }
}