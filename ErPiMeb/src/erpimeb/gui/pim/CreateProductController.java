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
import java.util.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author chris
 */
public class CreateProductController implements Initializable {

    private Stage stageRef;
    private Scene preSceneRef;
    private List<String> images = new ArrayList<>();
    private List<String> videos = new ArrayList<>();
    private ArrayList<Product> related = new ArrayList<>();
    private HashMap<String, String> specs = new HashMap<>();
    
    
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private TextArea descriptionTextArea;
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
    private ComboBox<String> keysComboBox;
    @FXML
    private TextField specValueTextField;
    @FXML
    private ListView<String> specListView;
    @FXML
    private Label statusLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CommodityManagerFacade comManager = CommodityManager.getInstance();
        
        List<String> specKeys = comManager.getAllSpecKeys();
        
        if(specKeys.isEmpty()) {
            System.out.println("wowoow");
        }
        System.out.println("not wow");
        
        keysComboBox.getItems().addAll(comManager.getAllSpecKeys()); // Skal hente keys fra database
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
    private void handleAttachImage(ActionEvent event) {
        images.add(imageUrlTextField.getText());
        imageListView.getItems().add(imageUrlTextField.getText());
    }

    @FXML
    private void handleAttachVideo(ActionEvent event) {
        videos.add(videoLinkTextField.getText());
        videoListView.getItems().add(videoLinkTextField.getText());
    }

    @FXML
    private void handleCreateProduct(ActionEvent event) {
        if (!nameTextField.getText().isEmpty() || !priceTextField.getText().isEmpty() || !descriptionTextArea.getText().isEmpty()) {
            CommodityManagerFacade comManager = CommodityManager.getInstance();
            comManager.createProduct(nameTextField.getText(), images, videos,
                    descriptionTextArea.getText(), specs,
                    Integer.parseInt(priceTextField.getText()), related);

            // Go back to PIM
            this.stageRef.setScene(this.preSceneRef);
            this.stageRef.setTitle("PIM Backend");
            this.stageRef.show();
        } else {
            statusLabel.setText("Name, price and description MUST be filled out.");
        }

    }

    @FXML
    private void handleAttachRelatedProduct(ActionEvent event) {
        
    }

    @FXML
    private void handleRemoveRelatedProduct(ActionEvent event) {
        
    }

    @FXML
    private void handleAddSpecifications(ActionEvent event) {
        if (specs.get(keysComboBox.getSelectionModel().getSelectedItem()) == null && keysComboBox.getSelectionModel().getSelectedItem() != null) {
            specs.put(keysComboBox.getSelectionModel().getSelectedItem(), specValueTextField.getText());
            specListView.getItems().add(keysComboBox.getSelectionModel().getSelectedItem() + " = " + specValueTextField.getText());
        }
    }

    @FXML
    private void handleSpecClick(MouseEvent event) {
        String[] parts = splitSpecification(specListView.getSelectionModel().getSelectedItem());
        specs.remove(parts[0]);
        specListView.getItems().remove(specListView.getSelectionModel().getSelectedItem());
    }

    private String[] splitSpecification(String string) {
        String[] parts = string.split(" = ");
        return parts;
    }

}
