/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.gui.webshop;

import erpimeb.domain.commoditymanager.Category;
import erpimeb.domain.usermanager.UserManager;
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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author DanielToft
 */
public class MainController implements Initializable {
    private Stage primaryStage;
    
    @FXML
    private TextField searchTerm;
    @FXML
    private ListView<Category> categoryListView;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void handleLogin(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/WebshopLogin.fxml"));
        Parent root = null;
        Scene scene;
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        LoginController loginController = (LoginController) loader.getController();
        loginController.setReferences(this.primaryStage,this.categoryListView.getScene());
        scene = new Scene(root);
        this.primaryStage.setScene(scene);
        this.primaryStage.setTitle("Bruger Login");
        this.primaryStage.show();
    }

    void setStageRef(Stage stage) {
        this.primaryStage = stage;
    }

    @FXML
    private void handleSearchForProduct(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/WebshopSearchForProduct.fxml"));
        Parent root = null;
        Scene scene;
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        SearchForProductController searchForProductController = (SearchForProductController) loader.getController();
        searchForProductController.setReferences(this.primaryStage,this.categoryListView.getScene());
        searchForProductController.setSearchTerm(this.searchTerm.getText());
        scene = new Scene(root);
        this.primaryStage.setScene(scene);
        this.primaryStage.setTitle("Søgning: " + this.searchTerm.getText());
        this.primaryStage.show();
    }

    @FXML
    private void handleChooseCategory(MouseEvent event) {
        if(this.categoryListView.getSelectionModel().getSelectedItem() != null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/WebshopSortProductByCategory.fxml"));
            Parent root = null;
            Scene scene;
            try {
                root = loader.load();
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
            SortProductByCategoryController sortProductByCategoryController = (SortProductByCategoryController) loader.getController();
            sortProductByCategoryController.setReferences(this.primaryStage,this.categoryListView.getScene());
            sortProductByCategoryController.setMainCategory(this.categoryListView.getSelectionModel().getSelectedItem());
            scene = new Scene(root);
            this.primaryStage.setScene(scene);
            this.primaryStage.setTitle(/*this.categoryListView.getSelectionModel().getSelectedItem().getName()*/"vis kategoriens navn her");
            this.primaryStage.show();
        }
    }

    @FXML
    private void handleEditProfile(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/WebshopEditCustomerProfile.fxml"));
        Parent root = null;
        Scene scene;
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        EditCustomerProfileController profileController = (EditCustomerProfileController) loader.getController();
        profileController.setReferences(this.primaryStage,this.categoryListView.getScene());
        scene = new Scene(root);
        this.primaryStage.setScene(scene);
        this.primaryStage.setTitle("Rediger profil");
        this.primaryStage.show();
    }
}
