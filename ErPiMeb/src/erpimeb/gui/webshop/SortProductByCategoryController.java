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

/**
 * FXML Controller class
 *
 * @author Agger
 */
public class SortProductByCategoryController implements Initializable, Switchable {
    private Category sortedBy;
    private CommodityManagerFacade cmf;
    @FXML
    private ListView<Category> subCategoryListview;
    @FXML
    private ListView<Product> foundProducts;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmf = CommodityManager.getInstance();
        // Fill category list view
        
        //THYGE
//        List<Category> subCategories = cmf.showSubCategories(cmf.getCurrentCategory());
//        for (Category c : subCategories) {
//            subCategoryListview.getItems().add(c);
//        }
//        List<Product> categoryProducts = cmf.showProducts();
//        for (Product p : categoryProducts) {
//            foundProducts.getItems().add(p);
//        }
        this.sortedBy = cmf.getCategory();
    }

    @FXML
    private void handleSelectSubCategory(MouseEvent event) {
        if(this.subCategoryListview.getSelectionModel().getSelectedItem() != null){
            cmf.setCategory(this.subCategoryListview.getSelectionModel().getSelectedItem());
            SceneSwitcher.changeScene("/resources/WebshopSortBySubCategory.fxml", "vis sub kategoriens navn her");
        }
    }

    @FXML
    private void handleChooseProduct(MouseEvent event) {
        if(this.foundProducts.getSelectionModel().getSelectedItem() != null){
            cmf.pickProductFromList(this.foundProducts.getSelectionModel().getSelectedItem());
            SceneSwitcher.changeScene("/resources/WebshopViewProduct.fxml", "vis produktets navn her");
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
