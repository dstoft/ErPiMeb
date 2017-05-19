/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.gui.pim;

import erpimeb.domain.commoditymanager.CommodityManager;
import erpimeb.domain.commoditymanager.CommodityManagerFacade;
import erpimeb.domain.commoditymanager.Product;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AKT
 */
public class PreEditProductController implements Initializable {

    private Stage stageRef;
    private Scene preSceneRef;

    private CommodityManagerFacade cManager = CommodityManager.getInstance();

    @FXML
    private TextField searchTextField;
    @FXML
    private ListView<Product> searchListView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    void setReferences(Stage stageRef, Scene preSceneRef) {
        this.stageRef = stageRef;
        this.preSceneRef = preSceneRef;
    }

    @FXML
    private void handleReturnToParent(ActionEvent event) {
        this.stageRef.setScene(this.preSceneRef);
        this.stageRef.setTitle("PIM Backend");
        this.stageRef.show();
    }

    @FXML
    private void handleSearch(ActionEvent event) {
        searchListView.getItems().clear();
        List<Product> productsFound = cManager.searchForProduct(searchTextField.getText());

        searchListView.getItems().addAll(productsFound);
    }

    @FXML
    private void handlePickProduct(ActionEvent event) {
        if (searchListView.getSelectionModel().getSelectedItem() != null) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/PimEditProduct.fxml"));
            Parent root = null;
            Scene scene;
            try {
                root = loader.load();
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
            EditProductController pimEditProductController = (EditProductController) loader.getController();
            pimEditProductController.setReferences(this.stageRef,this.searchTextField.getScene());
            pimEditProductController.setPickedProduct(searchListView.getSelectionModel().getSelectedItem());
            scene = new Scene(root);
            this.stageRef.setScene(scene);
            this.stageRef.setTitle("PIM Backend - Rediger Produkt");
            this.stageRef.show();
            
        }
    }

}
