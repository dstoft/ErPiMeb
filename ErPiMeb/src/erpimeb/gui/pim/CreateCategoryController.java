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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author kasper
 */
public class CreateCategoryController implements Initializable, Switchable{
    ObservableList obs = null;
    ObservableList obs2 = null;
    
    public CreateCategoryController() {
    }
    
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
    private TextField productTextField;
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
    
    
    private CommodityManagerFacade cmf = CommodityManager.getInstance();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
	
	cmf.showCategories();
    }

    @FXML
    private void handleReturnToParent(ActionEvent event) {
        SceneSwitcher.cycleBackward();
    }

    @FXML
    private void handleCreateCategory(ActionEvent event) {
	
	cmf.createCategory(nameTextField.getText(), subcategoryListview.getItems().sorted(), 
		tagsListview.getItems(), productsListview.getItems());
    }

    @FXML
    private void handleAddButton(ActionEvent event){
	
    if(subcategoriesAddButton.isPressed()){
	   subcategoryListview.getItems().add(subcategoriesDropdown.getSelectionModel().getSelectedItem());
	}
	
	if (tagsAddButton.isPressed()){
	    tagsListview.getItems().add(tagTextField.getText());
	}
	    
	if(productsAddButton.isPressed()){
		// search method is not implemented correct yet
//	    productsListview.getItems().add(cmf.searchForProduct(productTextField.getText()));
	}
    }

    @Override
    public void setupInternals() {
        
    }

   
    
    
}
