/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.gui.webshop;

import erpimeb.domain.commoditymanager.Category;
import erpimeb.domain.commoditymanager.Product;
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
 * @author Agger
 */
public class SortProductByCategoryController implements Initializable {
    private Stage stageRef;
    private Scene preSceneRef;
    private Category sortedBy;
    @FXML
    private ListView<Category> subCategoryListview;
    @FXML
    private ListView<Product> foundProducts;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    void setReferences(Stage primaryStage, Scene preSceneRef) {
        this.stageRef = primaryStage;
        this.preSceneRef = preSceneRef;
    }

    void setMainCategory(Category mainCategory) {
        this.sortedBy = mainCategory;
        /*
        for each product ID in the main category, we wish to query PIM module for that product IDs data
        And create a new ProductWrapper object to put in the foundproducts listview.
        */
//        for(Integer productId : this.sortedBy.getProductIds()){
//            ProductWrapper pw = new ProductWrapper(productId);
//            this.foundProducts.getItems().add(pw);
//        }
        /*
        for each sub category in the main category, we wish to query PIM module for that sub categories product ids
        This is so we can create a new product category wrapper object to put in the subcategory listview
        */
    }

    @FXML
    private void handleSelectSubCategory(MouseEvent event) {
        if(this.subCategoryListview.getSelectionModel().getSelectedItem() != null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/WebshopSortBySubCategory.fxml"));
            Parent root = null;
            Scene scene;
            try {
                root = loader.load();
            } catch (IOException ex) {
                Logger.getLogger(SortProductByCategoryController.class.getName()).log(Level.SEVERE, null, ex);
            }
            SortBySubCategoryController sortBySubCategoryController = (SortBySubCategoryController) loader.getController();
            sortBySubCategoryController.setReferences(this.stageRef,this.foundProducts.getScene(),this.stageRef.getTitle());
            sortBySubCategoryController.setSubCategory(this.subCategoryListview.getSelectionModel().getSelectedItem());
            scene = new Scene(root);
            this.stageRef.setScene(scene);
            this.stageRef.setTitle(/*this.subCategoryListview.getSelectionModel().getSelectedItem().getName()*/"vis sub kategoriens navn her");
            this.stageRef.show();
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
                Logger.getLogger(SearchForProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
            ViewProductController viewProductController = (ViewProductController) loader.getController();
            viewProductController.setReferences(this.stageRef,this.foundProducts.getScene(),this.stageRef.getTitle());
            viewProductController.setProduct(this.foundProducts.getSelectionModel().getSelectedItem());
            viewProductController.setupScene();
            scene = new Scene(root);
            this.stageRef.setScene(scene);
            this.stageRef.setTitle(/*this.foundProducts.getSelectionModel().getSelectedItem().getName()*/"vis produktets navn her");
            this.stageRef.show();
        }
    }

    @FXML
    private void handleReturnToParent(ActionEvent event) {
        this.stageRef.setScene(this.preSceneRef);
        this.stageRef.setTitle("Webshop");
        this.stageRef.show();
    }
    
}
