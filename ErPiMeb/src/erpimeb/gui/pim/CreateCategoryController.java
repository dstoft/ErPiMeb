/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.gui.pim;

import erpimeb.domain.commoditymanager.Category;
import erpimeb.domain.commoditymanager.CommodityManager;
import erpimeb.domain.commoditymanager.CommodityManagerFacade;
import erpimeb.domain.commoditymanager.Product;
import erpimeb.gui.SceneSwitcher;
import erpimeb.gui.Switchable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author kasper
 */
public class CreateCategoryController implements Initializable, Switchable {

    ObservableList obs = null;
    ObservableList obs2 = null;

    @FXML
    private Button returnButton;
    @FXML
    private TextField nameTextField;
    @FXML
    private Button createCategoryButton;
    @FXML
    private ComboBox<Category> subcategoriesDropdown;
    @FXML
    private TextField tagTextField;
    @FXML
    private Button subcategoriesAddButton;
    @FXML
    private Button productsAddButton;
    @FXML
    private Button tagsAddButton;
    @FXML
    private ListView<Category> subcategoryListview;
    @FXML
    private ListView<String> tagsListview;
    @FXML
    private ListView<Product> productsListview;

    private CommodityManagerFacade cmf;
    @FXML
    private Button updateProductListBt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    private void handleReturnToParent(ActionEvent event) {
        SceneSwitcher.cycleBackward();
    }

    @FXML
    private void handleCreateCategory(ActionEvent event) {
        if(cmf.createCategory(nameTextField.getText(), subcategoryListview.getItems().sorted(),tagsListview.getItems(), productsListview.getItems())) {
            SceneSwitcher.cycleBackward();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Oprettelsen af kategorien fejlede");
            alert.setHeaderText("Den nye kategori kunne ikke gemmes, tjek venligst kategoriens informationer.");
            alert.showAndWait();
        }
        
    }

    @FXML
    private void handleAddButton(ActionEvent event) {
        if (event.getSource() == this.subcategoriesAddButton) {
            Category selectedCategory = this.subcategoriesDropdown.getSelectionModel().getSelectedItem();
            if(!this.subcategoryListview.getItems().contains(selectedCategory)) {
                subcategoryListview.getItems().add(selectedCategory);
                this.subcategoriesDropdown.getSelectionModel().clearSelection();
                this.subcategoriesDropdown.getItems().remove(selectedCategory);
            }
        }
        if (event.getSource() == this.tagsAddButton) {
            tagsListview.getItems().add(tagTextField.getText());
            this.tagTextField.clear();
        }
        if(event.getSource() == this.productsAddButton) {
            SceneSwitcher.changeScene("/resources/PimSearchForProduct.fxml", "Search for product to add");
            
            Product selectedProduct = this.cmf.getCurrentProduct();
            if(selectedProduct == null) {
                return;
            }
            if(!this.productsListview.getItems().contains(selectedProduct)) {
                this.productsListview.getItems().add(selectedProduct);
            }
        }
        if(event.getSource() == this.updateProductListBt) {
            Product selectedProduct = this.cmf.getCurrentProduct();
            if(selectedProduct == null) {
                return;
            }
            if(!this.productsListview.getItems().contains(selectedProduct)) {
                this.productsListview.getItems().add(selectedProduct);
            }
        }
    }

    @FXML
    private void handleDropdown(ActionEvent event) {
        
    }

    @Override
    public void setupInternals() {
        this.cmf = CommodityManager.getInstance();
        this.cmf.setCurrentProduct(null);
        this.subcategoriesDropdown.getItems().addAll(this.cmf.getNonMainCategories());
    }
}
