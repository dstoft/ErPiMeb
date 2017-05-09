/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.webshop;

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
public class SortBySubCategoryController implements Initializable {
    private Stage stageRef;
    private Scene preSceneRef;
    private String preSceneTitle;
    private ProductCategoryWrapper sortedBy;
    @FXML
    private ListView<ProductWrapper> foundProducts;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    void setReferences(Stage stageRef, Scene preSceneRef,String preSceneTitle) {
        this.stageRef = stageRef;
        this.preSceneRef = preSceneRef;
        this.preSceneTitle = preSceneTitle;
    }

    void setSubCategory(ProductCategoryWrapper subCategory) {
        this.sortedBy = subCategory;
        /*
        for each product ID in the main category, we wish to query PIM module for that product IDs data
        And create a new ProductWrapper object to put in the foundproducts listview.
        */
        for(Integer productId : this.sortedBy.getProductIds()){
            ProductWrapper pw = new ProductWrapper(productId);
            this.foundProducts.getItems().add(pw);
        }
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
                Logger.getLogger(SortBySubCategoryController.class.getName()).log(Level.SEVERE, null, ex);
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

    @FXML
    private void handleReturnToParent(ActionEvent event) {
        this.stageRef.setScene(this.preSceneRef);
        this.stageRef.setTitle(this.preSceneTitle);
        this.stageRef.show();
    }
    
}
