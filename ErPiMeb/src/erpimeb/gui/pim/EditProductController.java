/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.gui.pim;

import erpimeb.domain.commoditymanager.Product;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author chris
 */
public class EditProductController implements Initializable {
    private Stage stageRef;
    private Scene preSceneRef;
    private Product pickedProduct;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private ListView<String> imageListView;
    @FXML
    private ListView<String> videoListView;
    @FXML
    private TextField imageUrlTextField;
    @FXML
    private TextField videoLinkTextField;
    @FXML
    private ComboBox<Product> allProductsComboBox;
    @FXML
    private ComboBox<Product> relatedProductsComboBox;
    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private TextField specValueTextField;
    @FXML
    private ComboBox<String> keysComboBox;
    @FXML
    private ListView<String> specListView;
    @FXML
    private Label statusLabel;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    void setReferences(Stage stageRef,Scene preSceneRef) {
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
    private void handleAttachImage(ActionEvent event) {
    }

    @FXML
    private void handleAttachVideo(ActionEvent event) {
    }

    @FXML
    private void handleSaveChanges(ActionEvent event) {
    }

    @FXML
    private void handleAttachRelatedProduct(ActionEvent event) {
    }

    @FXML
    private void handleRemoveRelatedProduct(ActionEvent event) {
    }

    @FXML
    private void handleAddSpecifications(ActionEvent event) {
    }

    @FXML
    private void handleSpecClick(MouseEvent event) {
    }
    
    public void setPickedProduct(Product pickedProduct) {
        this.pickedProduct = pickedProduct;
        nameTextField.setText(pickedProduct.getName());
    }
    
}
