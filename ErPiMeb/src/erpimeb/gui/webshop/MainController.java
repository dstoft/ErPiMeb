/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.gui.webshop;

import erpimeb.domain.commoditymanager.Category;
import erpimeb.domain.commoditymanager.CommodityManager;
import erpimeb.domain.commoditymanager.CommodityManagerFacade;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import erpimeb.gui.*;

/**
 *
 * @author DanielToft
 */
public class MainController implements Initializable, Switchable {
    private CommodityManagerFacade cmf;
    @FXML
    private TextField searchTerm;
    @FXML
    private ListView<Category> categoryListView;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmf = CommodityManager.getInstance();
    }    

    @FXML
    private void handleLogin(ActionEvent event) {
        SceneSwitcher.changeScene("/resources/WebshopLogin.fxml", "Bruger Login");
    }

    @FXML
    private void handleSearchForProduct(ActionEvent event) {
        cmf.setSearchTerm(this.searchTerm.getText());
        SceneSwitcher.changeScene("/resources/WebshopSearchForProduct.fxml", "Søgning: " + this.searchTerm.getText());
    }

    @FXML
    private void handleChooseCategory(MouseEvent event) {
        if(this.categoryListView.getSelectionModel().getSelectedItem() != null){
            cmf.setCategory(this.categoryListView.getSelectionModel().getSelectedItem());
            SceneSwitcher.changeScene("/resources/WebshopSortProductByCategory.fxml", "vis kategoriens navn her");
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
