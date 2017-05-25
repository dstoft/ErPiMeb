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
 * @author Agger
 */
public class SortProductByCategoryController implements Initializable {
    private Stage stageRef;
    private Scene preSceneRef;
    
    private CommodityManagerFacade cManager = CommodityManager.getInstance();
    
    @FXML
    private ListView<Category> subCategoryListview;
    @FXML
    private ListView<Product> foundProducts;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Fill category list view
        List<Category> subCategories = cManager.showSubCategories(cManager.getCurrentCategory());
        for (Category c : subCategories) {
            subCategoryListview.getItems().add(c);
        }
        List<Product> categoryProducts = cManager.showProducts();
        for (Product p : categoryProducts) {
            foundProducts.getItems().add(p);
        }
    }

    void setReferences(Stage primaryStage, Scene preSceneRef) {
        this.stageRef = primaryStage;
        this.preSceneRef = preSceneRef;
    }

    @FXML
    private void handleSelectSubCategory(MouseEvent event) {
        if(this.subCategoryListview.getSelectionModel().getSelectedItem() != null){
            Category pickedSubCategory = subCategoryListview.getSelectionModel().getSelectedItem();
            cManager.pickMainCategory(pickedSubCategory);
            
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
            scene = new Scene(root);
            this.stageRef.setScene(scene);
            this.stageRef.setTitle(pickedSubCategory.getName());
            this.stageRef.show();
        }
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
                Logger.getLogger(SearchForProductController.class.getName()).log(Level.SEVERE, null, ex);
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
        this.stageRef.setTitle("Webshop");
        this.stageRef.show();
    }
    
}
