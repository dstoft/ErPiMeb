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
 * @author chris
 */
public class SearchForProductController implements Initializable {
    private Stage stageRef;
    @FXML
    private ListView<ProductWrapper> foundProducts;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ProductWrapper pw = new ProductWrapper(1);
        this.foundProducts.getItems().add(pw);
    }    

    void setStageRef(Stage primaryStage) {
        this.stageRef = primaryStage;
    }

    @FXML
    private void handleChooseProduct(MouseEvent event) {
        if(this.foundProducts.getSelectionModel().getSelectedItem() != null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/WebshopViewProduct.fxml"));
            Parent root = null;
            Scene scene;
            try {
                root = loader.load();
            } catch (IOException ex) {
                Logger.getLogger(SearchForProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
            ViewProductController viewProductController = (ViewProductController) loader.getController();
            viewProductController.setStageRef(this.stageRef);
            viewProductController.setProduct(this.foundProducts.getSelectionModel().getSelectedItem());
            viewProductController.setupScene();
            scene = new Scene(root);
            this.stageRef.setScene(scene);
            this.stageRef.setTitle(this.foundProducts.getSelectionModel().getSelectedItem().getName());
            this.stageRef.show();
        }
    }
}
