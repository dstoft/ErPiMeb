/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.pim;

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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author chris
 */
public class MainController implements Initializable {
    private Stage stageRef;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setStageRef(Stage stageRef) {
        this.stageRef = stageRef;
    }

    @FXML
    private void handleCreateProduct(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/PimCreateProduct.fxml"));
        Parent root = null;
        Scene scene;
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        CreateProductController pimCreateProductController = (CreateProductController) loader.getController();
        pimCreateProductController.setStageRef(this.stageRef);
        scene = new Scene(root);
        this.stageRef.setScene(scene);
        this.stageRef.setTitle("PIM Backend - Opret Produkt");
        this.stageRef.show();
    }

    @FXML
    private void handleEditProduct(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/PimEditProduct.fxml"));
        Parent root = null;
        Scene scene;
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        EditProductController pimEditProductController = (EditProductController) loader.getController();
        pimEditProductController.setStageRef(this.stageRef);
        scene = new Scene(root);
        this.stageRef.setScene(scene);
        this.stageRef.setTitle("PIM Backend - Ændre Produkt");
        this.stageRef.show();
    }
}
