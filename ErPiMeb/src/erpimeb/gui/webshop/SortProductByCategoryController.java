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
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import erpimeb.gui.Switchable;
import erpimeb.gui.SceneSwitcher;
import java.util.HashSet;

/**
 * FXML Controller class
 *
 * @author Agger
 */
public class SortProductByCategoryController implements Initializable, Switchable {
    private Category sortedBy;
    private CommodityManagerFacade cManager;
    
    @FXML
    private ListView<Category> subCategoryListview;
    @FXML
    private ListView<Product> foundProducts;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cManager = CommodityManager.getInstance();
        this.sortedBy = cManager.getCategory();
        
        // Fill category list view
        List<Category> subCategories = cManager.showSubCategories(cManager.getCurrentCategory());
        for (Category c : subCategories) {
            subCategoryListview.getItems().add(c);
            this.cManager.setCategory(c);
            this.foundProducts.getItems().addAll(this.cManager.showProducts());
        }
        
        this.cManager.setCategory(this.sortedBy);
        this.foundProducts.getItems().addAll(this.cManager.showProducts());
    }

    @FXML
    private void handleSelectSubCategory(MouseEvent event) {
        if(this.subCategoryListview.getSelectionModel().getSelectedItem() != null){
            Category cat = this.subCategoryListview.getSelectionModel().getSelectedItem();
            cManager.setCategory(cat);
            SceneSwitcher.changeScene("/resources/WebshopSortBySubCategory.fxml", cat.getName());
        }
    }

    @FXML
    private void handleChooseProduct(MouseEvent event) {
        if(this.foundProducts.getSelectionModel().getSelectedItem() != null){
            Product pro = this.foundProducts.getSelectionModel().getSelectedItem();
            cManager.pickProductFromList(pro);
            SceneSwitcher.changeScene("/resources/WebshopViewProduct.fxml", pro.getName());
        }
    }

    @FXML
    private void handleReturnToParent(ActionEvent event) {
        SceneSwitcher.cycleBackward();
    }

    @Override
    public void setupInternals() {
        
    }
    
}
