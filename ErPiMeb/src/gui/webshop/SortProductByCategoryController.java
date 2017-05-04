/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.webshop;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private ProductCategoryWrapper sortedBy;
    @FXML
    private ListView<ProductCategoryWrapper> subCategoryListview;
    @FXML
    private ListView<ProductWrapper> foundProducts;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    void setStageRef(Stage primaryStage) {
        this.stageRef = primaryStage;
    }

    void setMainCategory(ProductCategoryWrapper mainCategory) {
        this.sortedBy = mainCategory;
    }

    @FXML
    private void handleSelectSubCategory(MouseEvent event) {
        if(this.subCategoryListview.getSelectionModel().getSelectedItem() != null){
            
        }
    }

    @FXML
    private void handleViewProduct(MouseEvent event) {
        if(this.foundProducts.getSelectionModel().getSelectedItem() != null){
            
        }
    }
    
}
