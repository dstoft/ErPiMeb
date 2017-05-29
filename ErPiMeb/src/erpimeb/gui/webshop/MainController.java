/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.gui.webshop;

import erpimeb.domain.commoditymanager.Category;
import erpimeb.domain.commoditymanager.CommodityManager;
import erpimeb.domain.commoditymanager.CommodityManagerFacade;
import erpimeb.domain.usermanager.UserManager;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import erpimeb.gui.Switchable;
import erpimeb.gui.SceneSwitcher;

/**
 *
 * @author DanielToft
 */
public class MainController implements Initializable, Switchable {
    private CommodityManagerFacade cManager;
    
    @FXML
    private TextField searchTerm;
    @FXML
    private ListView<Category> categoryListView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cManager = CommodityManager.getInstance();
        this.categoryListView.getItems().addAll(cManager.showMainCategories());
    }    

    @FXML
    private void handleLogin(ActionEvent event) {
        SceneSwitcher.changeScene("/resources/WebshopLogin.fxml", "Bruger Login");
    }
    

    @FXML
    private void handleSearchForProduct(ActionEvent event) {
        cManager.setSearchTerm(this.searchTerm.getText());
        SceneSwitcher.changeScene("/resources/WebshopSearchForProduct.fxml", "Søgning: " + this.searchTerm.getText());
    }

    @FXML
    private void handleChooseCategory(MouseEvent event) {
        if(this.categoryListView.getSelectionModel().getSelectedItem() != null){
            Category cat = this.categoryListView.getSelectionModel().getSelectedItem();
            cManager.setCategory(cat);
            SceneSwitcher.changeScene("/resources/WebshopSortProductByCategory.fxml", cat.getName());
        }
    }

    @FXML
    private void handleEditProfile(ActionEvent event) {
        SceneSwitcher.changeScene("/resources/WebshopEditCustomerProfile.fxml", "Rediger profil");
    }

    @Override
    public void setupInternals() {
    }


}
