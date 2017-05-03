/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.webshop;

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
    private ListView<ProductCategoryWrapper> categoryListView;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ProductCategoryWrapper mainCategory = new ProductCategoryWrapper("Hvidevarer",1,2);
        ProductCategoryWrapper secondCategory = new ProductCategoryWrapper("Vaskemaskiner",3);
        ProductCategoryWrapper thirdCategory = new ProductCategoryWrapper("Køleskabe",4,5);
        
        mainCategory.addSubCategories("Vaskemaskiner","Køleskabe");
        this.categoryListView.getItems().add(mainCategory);
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
        loginController.setStageRef(this.primaryStage);
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
        searchForProductController.setStageRef(this.primaryStage);
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
            sortProductByCategoryController.setStageRef(this.primaryStage);
            sortProductByCategoryController.setMainCategory(this.categoryListView.getSelectionModel().getSelectedItem());
            scene = new Scene(root);
            this.primaryStage.setScene(scene);
            this.primaryStage.setTitle(this.categoryListView.getSelectionModel().getSelectedItem().getName());
            this.primaryStage.show();
        }
    }
}
