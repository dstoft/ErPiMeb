/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.gui.webshop;

import erpimeb.domain.commoditymanager.Category;
import erpimeb.domain.commoditymanager.CommodityManager;
import erpimeb.domain.commoditymanager.CommodityManagerFacade;
import erpimeb.domain.commoditymanager.Product;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author chris
 */
public class SortBySubCategoryController implements Initializable {
    private Stage stageRef;
    private Scene preSceneRef;
    private String preSceneTitle;
    
    private CommodityManagerFacade cManager = CommodityManager.getInstance();
    
    @FXML
    private ListView<Product> foundProducts;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Product> categoryProducts = cManager.showProducts();
        for (Product p : categoryProducts) {
            foundProducts.getItems().add(p);
        }
    }    

    void setReferences(Stage stageRef, Scene preSceneRef,String preSceneTitle) {
        this.stageRef = stageRef;
        this.preSceneRef = preSceneRef;
        this.preSceneTitle = preSceneTitle;
    }

    @FXML
    private void handleChooseProduct(MouseEvent event) {
        if(this.foundProducts.getSelectionModel().getSelectedItem() != null){
            Product pickedProduct = foundProducts.getSelectionModel().getSelectedItem();
            cManager.setCurrentProduct(pickedProduct);
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/WebshopViewProduct.fxml"));
            Parent root = null;
            Scene scene;
            try {
                root = loader.load();
            } catch (IOException ex) {
                Logger.getLogger(SortBySubCategoryController.class.getName()).log(Level.SEVERE, null, ex);
            }
            ViewProductController viewProductController = (ViewProductController) loader.getController();
            viewProductController.setReferences(this.stageRef,this.foundProducts.getScene(),this.stageRef.getTitle());
            viewProductController.setupScene();
            scene = new Scene(root);
            this.stageRef.setScene(scene);
            this.stageRef.setTitle(pickedProduct.getName());
            this.stageRef.show();
        }
    }

    @FXML
    private void handleReturnToParent(ActionEvent event) {
        this.stageRef.setScene(this.preSceneRef);
        this.stageRef.setTitle(this.preSceneTitle);
        this.stageRef.show();
    }
    
}
